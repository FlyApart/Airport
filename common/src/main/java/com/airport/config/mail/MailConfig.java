package com.airport.config.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

	private final MailPropertiesConfig mailPropertiesConfig;

	@Bean
	public JavaMailSender getMailSender(){
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl ();
		mailSender.setHost (mailPropertiesConfig.getHost ());
		mailSender.setPort (mailPropertiesConfig.getPort ());
		mailSender.setProtocol (mailPropertiesConfig.getProtocol ());
		mailSender.setUsername (mailPropertiesConfig.getUsername ());
		mailSender.setPassword (mailPropertiesConfig.getPassword ());

		Properties properties = mailSender.getJavaMailProperties ();

		properties.setProperty ("mail.debug",mailPropertiesConfig.getDebug ());

		return mailSender;
	}
}
