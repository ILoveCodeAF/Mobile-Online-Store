package com.example.nhom11.model;

import java.util.Date;

public class Person {
	
	private long id;
	private String name, address, email, phone;
	private Date dob;
	private Account account;
	
	public Person() {
		super();
	}

	public Person(long id, String name, String address, String email, String phone, Date dob, Account account) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.dob = dob;
		this.account= account;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", address=" + address + ", email=" + email + ", phone=" + phone
				+ ", dob=" + dob + ", account=" + account.toString() + "]";
	}

}
