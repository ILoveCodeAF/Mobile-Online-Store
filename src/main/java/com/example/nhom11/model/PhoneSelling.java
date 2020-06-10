package com.example.nhom11.model;

public class PhoneSelling {

	private long id;
	private Phone phone;
	private int quantity;
	
	public PhoneSelling() {
		super();
	}

	public PhoneSelling(long id, Phone phone, int quantity) {
		super();
		this.id = id;
		this.phone = phone;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "PhoneSelling [id=" + id + ", phone=" + phone.toString() + ", quantity=" + quantity + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (phone.getId() ^ (phone.getId() >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhoneSelling other = (PhoneSelling) obj;
		if (phone.getId() != other.getPhone().getId())
			return false;
		return true;
	}

	
	
}
