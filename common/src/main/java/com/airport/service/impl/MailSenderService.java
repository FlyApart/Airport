package com.airport.service.impl;

import com.airport.config.mail.MailPropertiesConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

	private final MailPropertiesConfig mailPropertiesConfig;

	private final JavaMailSender mailSender;

	public void sendEmail (String emailTo, String subject, String message){

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage ();

		simpleMailMessage.setFrom (mailPropertiesConfig.getUsername ());
		simpleMailMessage.setTo (emailTo);
		simpleMailMessage.setSubject (subject);
		simpleMailMessage.setText (message);

		mailSender.send (simpleMailMessage);
	}
}
