package com.example.nhom11.model;

import java.util.Date;

public class Shipment {
	
	private long id;
	private String address;
	private String phone;
	private Date date;
	private float price;
	
	public Shipment() {
		super();
	}

	public Shipment(long id, String address, String phone, Date date, float price) {
		super();
		this.id = id;
		this.address = address;
		this.phone = phone;
		this.date = date;
		this.price = price;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Shipment [id=" + id + ", address=" + address + ", phone=" + phone + ", date=" + date + ", price="
				+ price + "]";
	}

}
