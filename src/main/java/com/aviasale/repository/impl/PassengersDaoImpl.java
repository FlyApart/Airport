package com.aviasale.repository.impl;

import com.aviasale.entity.Passengers;
import com.aviasale.repository.PassengersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
@Transactional
public class PassengersDaoImpl implements PassengersDao {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String SURNAME = "surname";
	public static final String LOGIN = "login";
	public static final String PASSWORD = "password";
	public static final String COUNTRY = "country";
	public static final String CREATED = "created";
	public static final String CHANGED = "changed";
	public static final String BIRTH_DATE = "birth_date";


	private final JdbcTemplate jdbcTemplate;
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private Passengers getPassengersRowMapper (ResultSet resultSet, int i) throws SQLException {
		Passengers passengers = new Passengers ();
		passengers.setId (resultSet.getLong (ID));
		passengers.setName (resultSet.getString (NAME));
		passengers.setSecondName (resultSet.getString (SURNAME));
		passengers.setLogin (resultSet.getString (LOGIN));
		passengers.setPassword (resultSet.getString (PASSWORD));
		passengers.setCountry (resultSet.getLong (COUNTRY));
		passengers.setCreated (resultSet.getTimestamp (CREATED));
		passengers.setChanged (resultSet.getTimestamp (CHANGED));
		passengers.setBirthDate (resultSet.getTimestamp (BIRTH_DATE));
		return passengers;
	}

	final String findAll = "select * from passengers";

	final String findById = "select * from passengers where id = :id";

	final String delete = "delete from passengers where id = :id";

	final String save = "INSERT INTO passengers (id, name, surname, login, password, id_country, created, date_birth) " +
			                    "VALUES (:id, :name, :surname, :login, :password, :country, :created, :birth_date)";

	final String update = "UPDATE passengers set name = :name, surname = :surname, login = :login, password = :password, id_country = :country" +
			                      "created = :created, changed = :changed,date_birth = :birth_date where id = :id";



	@Override
	public List<Passengers> findAll () {
		return namedParameterJdbcTemplate.query(findAll,this::getPassengersRowMapper);
	}

	@Override
	public Passengers findById (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		return namedParameterJdbcTemplate.queryForObject(findById,param,this::getPassengersRowMapper);
	}

	@Override
	public void delete (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id", id);
		namedParameterJdbcTemplate.update(delete,param);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, isolation = Isolation.DEFAULT)
	public Passengers save (Passengers entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder ();
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", entity.getName ());
		params.addValue("surname", entity.getSecondName ());
		params.addValue("login", entity.getLogin());
		params.addValue("password", entity.getPassword());
		params.addValue("country", entity.getCountry ());
		params.addValue("created", entity.getCreated ());
		namedParameterJdbcTemplate.update(save, params, keyHolder, new String[]{"id"});
		long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();
		return findById(createdUserId);
	}

	@Override
	public Passengers update (Passengers entity) {
		MapSqlParameterSource params = new MapSqlParameterSource ();
		params.addValue("name", entity.getName ());
		params.addValue("surname", entity.getSecondName ());
		params.addValue("login", entity.getLogin());
		params.addValue("password", entity.getPassword());
		params.addValue("country", entity.getCountry ());
		params.addValue("created", entity.getCreated ());
		 namedParameterJdbcTemplate.update(update,params);
		return findById(entity.getId ());
	}
}
