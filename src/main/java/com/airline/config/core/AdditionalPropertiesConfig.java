package com.airline.config.core;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties ("spring.jpa")
public class AdditionalPropertiesConfig {


	private String show_sql;
    private JpaHibernateConfig hibernate;

	@Data
	 public static class  JpaHibernateConfig{
	    private String ddl_auto;
    }




    @Bean(value = "jpaProperties")
    @Scope("singleton")
    @Primary
    public Properties getAdditionalProperties()  {
    	Properties properties = new Properties ();
		properties.put ("hibernate.show_sql",show_sql);
		properties.put ("hibernate.hbm2ddl.auto", hibernate.ddl_auto);

	    return properties;
	}
}
