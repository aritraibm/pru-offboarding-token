package com.pru.token.app.otp;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") 
    private String sender;

    @Override
	public String sendMail(EmailDetails details) throws MessagingException {
            MimeMessage ms= javaMailSender.createMimeMessage();
            ms.setRecipients(Message.RecipientType.TO,details.getRecipient());
            ms.setSubject(details.getSubject());
            ms.setText(details.getMsgBody());
            javaMailSender.send(ms);
            return "Mail Sent Successfully...";
	}

}
