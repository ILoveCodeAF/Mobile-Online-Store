package com.example.nhom11.demo;

import javax.mail.MessagingException;

import com.example.nhom11.utils.SendMailUtil;

public class Run {
	public static void main(String[] args) {
		
		try {
			SendMailUtil.sendMailWithPlainText("link", "http://localhost:8080/SmartPhoneStoreOnline/verify?code=gpseure", 
					"tuanvuonganhtuan@gmail.com");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
}
