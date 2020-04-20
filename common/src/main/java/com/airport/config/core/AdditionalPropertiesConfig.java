package com.airport.config.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("spring.jpa.hibernate")
public class AdditionalPropertiesConfig {

	@Value ("${spring.jpa.show-sql}")
	private String show_sql;

	private String ddl_auto;
	private String use_second_level_cache;
	private String region_factory_class;
	private String provider;


	@Bean(value = "jpaProperties")
	@Scope("singleton")
	@Primary
	public Properties getAdditionalProperties () {
		Properties properties = new Properties ();
		properties.put ("hibernate.show_sql", show_sql);
		properties.put ("hibernate.hbm2ddl.auto", ddl_auto);
		properties.put ("hibernate.cache.use_second_level_cache", use_second_level_cache);
		properties.put ("hibernate.cache.region.factory_class", region_factory_class);
		properties.put ("hibernate.javax.cache.provider", provider);

		return properties;
	}
}
