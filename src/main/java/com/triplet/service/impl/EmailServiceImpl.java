package com.triplet.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.triplet.controller.web.MailController;
import com.triplet.service.EmailService;

public class EmailServiceImpl implements EmailService {
	private String helperAddress = "nguyen.thanh.huyen@sun-asterisk.com";

	private static final Logger logger = Logger.getLogger(MailController.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
	public boolean sendSimpleMessage(String subject, String text) {
		// takes input from e-mail form

		// prints debug info
		System.out.println("To: " + helperAddress);
		System.out.println("Subject: " + subject);
		System.out.println("Message: " + text);

		// creates a simple e-mail object
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(helperAddress);
		email.setSubject(subject);
		email.setText(text);
		try {
			getMailSender().send(email);
			return true;
		} catch (Exception e) {
			logger.error(e);
			return false;
		}
	}
}
