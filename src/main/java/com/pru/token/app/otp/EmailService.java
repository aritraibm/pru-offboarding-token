package com.pru.token.app.otp;

import javax.mail.MessagingException;

public interface EmailService {

	String sendMail(EmailDetails details) throws MessagingException;
}
