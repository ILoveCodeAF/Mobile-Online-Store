package com.example.nhom11.dao;

import com.example.nhom11.model.Customer;

public interface CustomerDAOTuan {
	
	//Them moi mot Customer vao DB
	public Customer add(Customer customer);
	
	//Tra ve id cua Customer neu ton tai
	//Tra ve 0 neu Customer chua co tai khoan
	public long checkIfGoogleAccountExist(String googleId);
	
	//Tra ve Id cua Customer neu ton tai
	//Tra ve 0 neu Customer chua ton tai
	public long checkIfFbAccountExist(String fbId);
}
