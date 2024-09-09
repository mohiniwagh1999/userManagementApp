package com.management.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
@Service
public class EmailService {
	@Autowired
	public JavaMailSender mailSender;
	
	public boolean sendEmail(String to,String subject,String body)
	{
		
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage);
			helper.setSubject(subject);
			helper.setText(body);
			helper.setTo(to);
			mailSender.send(mimeMessage);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return true;
	}

}
