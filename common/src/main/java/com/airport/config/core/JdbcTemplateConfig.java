package com.airport.config.core;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;

import javax.persistence.EntityManagerFactory;

@Configuration
public class JdbcTemplateConfig {

	@Autowired
	private BasicDataSource dataSource;

	//https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/jdbc.html
	//https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
	@Bean("jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}

	@Bean("namedJdbcTemplate")
	public NamedParameterJdbcTemplate getNamedJdbcTemplate() {
		return new NamedParameterJdbcTemplate(dataSource);
	}

	/*@Bean("transactionManager")
	public DataSourceTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(dataSource);
	}*/

	@Bean("transactionManager")
	public JpaTransactionManager getTransactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

	//https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/aop.html#aop-understanding-aop-proxies
	//https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/transaction.html

}