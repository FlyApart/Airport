package com.aviasale.repository.impl;

import com.aviasale.repository.PassengersDao;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;



	@Override
	public List findAll () {
		return null;
	}

	@Override
	public Object findById (Object id) {
		return null;
	}

	@Override
	public void delete (Object id) {

	}

	@Override
	public Object save (Object entity) {
		return null;
	}

	@Override
	public Object update (Object entity) {
		return null;
	}
}
