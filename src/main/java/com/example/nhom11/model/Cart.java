package com.example.nhom11.model;

import java.util.HashSet;
import java.util.Set;

public class Cart {

	private long id;
	private Customer customer;
	private Set<PhoneSelling> phones;
	
	public Cart() {
		phones=new HashSet<>();
	}
	public Cart(long id, Customer customer, Set<PhoneSelling> phones) {
		this.id = id;
		this.customer = customer;
		this.phones = phones;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Set<PhoneSelling> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneSelling> phones) {
		this.phones = phones;
	}
	
	public void addPhone(PhoneSelling phone) {
		this.getPhones().add(phone);
	}
	@Override
	public String toString() {
		return "Cart [id=" + id + ", customer=" + customer + ", phones=" + phones + "]";
	}
	
}
