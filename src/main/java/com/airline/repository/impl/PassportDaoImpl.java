package com.airline.repository.impl;

import com.airline.entity.Passports;
import com.airline.repository.PassportDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class PassportDaoImpl implements PassportDao {
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public static final String ID = "id";
	public static final String SERIES = "series";
	public static final String NUMBERS = "number";
	public static final String PASSENGERS_ID = "id_passengers";

	private Passports getPassportsRowMapper (ResultSet resultSet, int i) throws SQLException {
	Passports passports = new Passports ();
	passports.setId (resultSet.getLong (ID));
	passports.setSeries (resultSet.getLong (SERIES));
	passports.setNumber (resultSet.getLong (NUMBERS));
	passports.setPassengersId (resultSet.getLong (PASSENGERS_ID ));
	return  passports;
	}


	final String findAll = "select * from passport";

	final String findById = "select * from passport where id = :id";

	final String findByPassengersId = "select *  from passport where id_passengers = :id_passengers";

	final String delete = "delete from passport where id = :id";

	final String save = "INSERT INTO passport (series, number, id_passengers) " +
			                    "VALUES (:series, :number, :id_passengers)";

	final String update = "UPDATE passport set series = :series, number = :number where id = :id";


	@Override
	public List<Passports> findAll () {
		return namedParameterJdbcTemplate.query (findAll,this::getPassportsRowMapper);
	}

	@Override
	public Passports findById (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		return namedParameterJdbcTemplate.queryForObject (findById,param,this::getPassportsRowMapper);
	}

	@Override
	public void delete (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		namedParameterJdbcTemplate.update (delete,param);
	}

	@Override
	public Passports save (Passports entity) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("series",entity.getSeries ());
		param.addValue ("number",entity.getNumber ());
		param.addValue ("id_passengers",entity.getPassengersId ());
		KeyHolder keyHolder = new GeneratedKeyHolder ();

		namedParameterJdbcTemplate.update(save, param, keyHolder, new String[]{"id"});
		long createdId = Objects.requireNonNull(keyHolder.getKey()).longValue();
		return findById(createdId);
	}

	@Override
	public Passports update (Passports entity) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("series",entity.getSeries ());
		param.addValue ("number",entity.getNumber ());
		param.addValue ("id",entity.getId ());
		namedParameterJdbcTemplate.update (update,param);
		return findById(entity.getId ());
	}

	@Override
	public List<Passports> findByPassengersId (Long passengersId) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id_passengers", passengersId);
		return namedParameterJdbcTemplate.query (findByPassengersId,param,this::getPassportsRowMapper);
	}
}
