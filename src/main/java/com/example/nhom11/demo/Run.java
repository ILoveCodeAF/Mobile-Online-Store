package com.example.nhom11.demo;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.nhom11.utils.SendMailUtil;

public class Run {
	public static void main(String[] args) throws AddressException, MessagingException {
		SendMailUtil.sendMailWithPlainText("Dm Hieu", "chao cu", "leconghieu1998@gmail.com");
	}
}
