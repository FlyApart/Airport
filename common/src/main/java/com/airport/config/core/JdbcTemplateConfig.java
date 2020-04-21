package com.airport.config.core;

import lombok.RequiredArgsConstructor;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
@RequiredArgsConstructor
public class JdbcTemplateConfig {

	private final BasicDataSource dataSource;

	@Bean("jdbcTemplate")
	public JdbcTemplate getJdbcTemplate () {
		return new JdbcTemplate (dataSource);
	}

	@Bean("namedJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedJdbcTemplate () {
		return new NamedParameterJdbcTemplate (dataSource);
	}

	@Bean("transactionManager")
	public DataSourceTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean("transactionManager")
	public JpaTransactionManager getTransactionManager (EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager (entityManagerFactory);
	}


}