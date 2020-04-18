package com.airport.config.mail;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@Configuration
@ConfigurationProperties("spring.mail")
public class MailPropertiesConfig {

	private String host;
	private String username;
	private String password;
	private Integer port;
	private String protocol;
	private String debug;

}
