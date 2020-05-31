package com.example.nhom11.model;

import java.util.Date;

public class Customer extends Person {
	
	public Customer(long id, String name, String address, String email, String phone, Date dob, Account account) {
		super(id, name, address, email, phone, dob, account);
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	
	
}
