package com.example.nhom11.model;

import java.util.Date;

public class Admin extends Person {

	public Admin() {
		
	}
	public Admin(long id, String name, String address, String email, String phone, Date dob, Account account) {
		super(id, name, address, email, phone, dob, account);
	}
	
}
