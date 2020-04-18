package com.airport.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties("jwt-token")
@Configuration
public class SecurityConstants {

	private String secret;

	private Integer expire;

	private String prefix;

	private String header;

}
