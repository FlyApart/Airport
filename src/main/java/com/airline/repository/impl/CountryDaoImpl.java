package com.airline.repository.impl;

import com.airline.entity.Country;
import com.airline.repository.CountryDao;
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

@Repository
@Transactional
@RequiredArgsConstructor
public class CountryDaoImpl  implements CountryDao {

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String POPULATION = "population";
	public static final String LOCATION = "location";

	public Country getCountryRowMapper (ResultSet resultSet, int i) throws SQLException {
		Country country = new Country ();
		country.setId (resultSet.getLong (ID));
		country.setName (resultSet.getString (NAME));
		country.setPopulation (resultSet.getLong (POPULATION));
		country.setLocation (resultSet.getString (LOCATION));
		return country;
	}
		@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	final String findAll = "select * from country";

	final String findById = "select * from country where id = :id";

	final String delete = "delete from country where id = :id";

	final String save = "INSERT INTO country (name, population, location) " +
			                    "VALUES (:name, :population, :location)";

	final String update = "UPDATE country set name = :name, population = :population where id = :id";

	@Override
	public List<Country> findAll () {
		return namedParameterJdbcTemplate.query (findAll,this::getCountryRowMapper);
	}

	@Override
	public Country findById (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		return namedParameterJdbcTemplate.queryForObject (findById,param,this::getCountryRowMapper);
	}

	@Override
	public void delete (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		namedParameterJdbcTemplate.update (delete,param);
	}

	@Override
	public Country save (Country entity) {
		MapSqlParameterSource params = new MapSqlParameterSource ();
		params.addValue ("name", entity.getName ());
		params.addValue ("population", entity.getPopulation ());
		params.addValue ("location", entity.getLocation ());
		KeyHolder keyHolder = new GeneratedKeyHolder ();
		namedParameterJdbcTemplate.update (save,params,keyHolder,new String[]{"id"});
		return findById(keyHolder.getKey().longValue ());
	}

	@Override
	public Country update (Country entity) {
		MapSqlParameterSource params = new MapSqlParameterSource ();
		params.addValue ("name", entity.getName ());
		params.addValue ("population", entity.getPopulation ());
		params.addValue ("id", entity.getId ());
		namedParameterJdbcTemplate.update (update,params);
		return findById(entity.getId ());
	}
}
