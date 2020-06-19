package com.example.nhom11.dao;

import com.example.nhom11.model.Person;

public interface AccountDAOTuan {
	
	//Neu login thanh cong -> Tra ve Person tuong ung, neu khong se tra ve null
	public Person login(String username, String password);

}
