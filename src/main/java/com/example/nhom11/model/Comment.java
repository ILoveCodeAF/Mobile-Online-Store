package com.example.nhom11.model;

import java.util.Date;

public class Comment {
	private long id;
	private String content;
	private Date date;
	private Customer customer;
	private Phone phone;
	
	public Comment() {
		super();
	}

	public Comment(long id, String content, Date date, Customer customer, Phone phone) {
		super();
		this.id = id;
		this.content = content;
		this.date = date;
		this.customer = customer;
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
}
