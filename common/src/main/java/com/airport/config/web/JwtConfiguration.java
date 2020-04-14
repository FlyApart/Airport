package com.airport.config.web;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@ConfigurationProperties ("jwt-token")
@Configuration
public class JwtConfiguration {

	private String secret;

	private Integer expire;

}
