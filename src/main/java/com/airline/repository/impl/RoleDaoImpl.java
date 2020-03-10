package com.airline.repository.impl;

import com.airline.entity.Role;
import com.airline.repository.RoleDao;
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
public class RoleDaoImpl implements RoleDao {
	@Autowired
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private static String ID = "id";
	private static String PASSENGERS_ID = "passengers_id";
	private static String ROLE = "roles";

	@Override
	public List<Role> getRolesByPassengersId (Long passengersId) {
		return null;
	}

	@Override
	public List<Role> findAll () {
		return null;
	}

	@Override
	public Role findById (Long id) {
		return null;
	}

	@Override
	public void delete (Long id) {

	}

	@Override
	public Role save (Role entity) {
		return null;
	}

	@Override
	public Role update (Role entity) {
		return null;
	}

	/*private Role getRoleRowMapper (ResultSet resultSet,int i) throws SQLException {
		Role role = new Role ();
		role.setId (resultSet.getLong (ID));
		role.setPassengerId (resultSet.getLong (PASSENGERS_ID));
		role.setRole (resultSet.getString (ROLE));
		return role;
	}

	final String findAll = "select * from role";

	final String findById = "select * from role where id = :id";

	final String delete = "delete from role where id = :id";

	final String save = "INSERT INTO role (id, roles, passengers_id) " +
			                    "VALUES (:id, :roles, :passengers_id)";

	final String update = "UPDATE role set roles = :roles where id = :id";

	final String search = "select * from role where passengers_id = :passengers_id";


	@Override
	public List<Role> findAll () {
		return namedParameterJdbcTemplate.query (findAll,this::getRoleRowMapper);
	}

	@Override
	public Role findById (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		return namedParameterJdbcTemplate.queryForObject (findById,param,this::getRoleRowMapper);
	}

	@Override
	public void delete (Long id) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",id);
		namedParameterJdbcTemplate.update (delete,param);
	}

	@Override
	public Role save (Role entity) {
		KeyHolder keyHolder = new GeneratedKeyHolder ();
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("passengers_id",entity.getPassengerId ());
		param.addValue ("roles",entity.getRole ());
		namedParameterJdbcTemplate.update(save, param, keyHolder, new String[]{"id"});
		long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();
		return findById(createdRoleId);
	}

	@Override
	public Role update (Role entity) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("id",entity.getId ());
		param.addValue ("passengers_id",entity.getPassengerId ());
		param.addValue ("roles",entity.getRole ());
		namedParameterJdbcTemplate.update (update,param);
		return findById(entity.getId ());
	}

	@Override
	public List<Role> getRolesByPassengersId (Long passengersId) {
		MapSqlParameterSource param = new MapSqlParameterSource ();
		param.addValue ("passengers_id", passengersId);
		return namedParameterJdbcTemplate.query(search,param,this::getRoleRowMapper);
	}*/
}
