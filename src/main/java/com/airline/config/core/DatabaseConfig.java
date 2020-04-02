package com.airline.config.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

import java.util.Objects;

import static java.util.Optional.ofNullable;

@Setter
@Getter
@NoArgsConstructor
@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

    private static final Logger LOG = LogManager.getLogger (IllegalArgumentException.class);

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
        initialSizeDef = ofNullable(Integer.valueOf (Objects.requireNonNull(initialSize)))
                                      .orElseThrow (IllegalArgumentException::new);

        maxActiveDef = ofNullable(Integer.valueOf (Objects.requireNonNull(maxActive)))
                                         .orElseThrow (IllegalArgumentException::new);

        dataSource.setMaxActive(maxActiveDef);
        dataSource.setInitialSize (initialSizeDef);
        return dataSource;
    }
}
