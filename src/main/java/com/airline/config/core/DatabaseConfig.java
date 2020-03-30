package com.airline.config.core;

import com.airline.controller.DefaultExceptionHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

    private static final Logger LOG = LogManager.getLogger (DefaultExceptionHandler.class);

    private String driverName;

    private String url;

    private String login;

    private String password;

    private String initialSize;

    private String maxActive;

    private Integer initialSizeDef = 0;

    private Integer maxActiveDef = 5;

    @Bean(value = "dataSource", destroyMethod = "close")
    @Scope("singleton")
    @Primary
    public BasicDataSource getDatasource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driverName);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setUsername(login);

        try {
             initialSizeDef = Integer.valueOf (Objects.requireNonNull(initialSize));
        }
        catch (Exception e){
            LOG.error (e);
            dataSource.setInitialSize (initialSizeDef);
        }

        try {
            maxActiveDef = Integer.valueOf( Objects.requireNonNull(maxActiveDef));
        } catch (Exception e){
            LOG.error (e);
            dataSource.setMaxActive(maxActiveDef); // отлавливать ошибки 50.40 26.03.2020
        }

        return dataSource;
    }
}
