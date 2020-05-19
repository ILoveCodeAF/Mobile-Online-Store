package com.example.nhom11.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailUtil {

	public static void sendMailWithPlainText(String subject, String content, String recipient) throws AddressException, MessagingException {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", 587);
		Session s = Session.getInstance(p, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ptpmhuongdv.nhom11@gmail.com", "smartphonestoreonline");
			}
		});
		Message msg = new MimeMessage(s);
		msg.setFrom(new InternetAddress("ptpmhuongdv.nhom11@gmail.com"));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
		msg.setSubject(subject);
		msg.setText(content);
		
		Transport.send(msg);
	}
	
}
