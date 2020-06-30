package com.example.nhom11.model;

import java.util.Date;

public class Order {

	private long id;
	private Date date;
	private Cart cart;
	private ReceivingType receivingType;
	private PaymentType paymentType;
	private Shipment shipment;

	public Order() {
	}

	public Order(long id, Date date, Cart cart, ReceivingType receivingType, PaymentType paymentType,
			Shipment shipment) {
		this.id = id;
		this.date = date;
		this.cart = cart;
		this.receivingType = receivingType;
		this.paymentType = paymentType;
		this.shipment = shipment;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public ReceivingType getReceivingType() {
		return receivingType;
	}

	public void setReceivingType(ReceivingType receivingType) {
		this.receivingType = receivingType;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}	
	
}
