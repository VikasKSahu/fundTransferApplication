package com.se.codingChallange.fundTransferApplication.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class FundTransferEmailService {
	    @Autowired
	    private JavaMailSender emailSender;

	    public void sendSimpleMessage(FundTransferMail mail){
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setSubject(mail.getSubject());
	        message.setText(mail.getContent());
	        message.setTo(mail.getTo());
	        message.setFrom(mail.getFrom());

	        emailSender.send(message);
	    }
}
