package com.example.nhom11.dao;

import java.util.List;

import com.example.nhom11.model.Phone;

public interface PhoneDAOTuan {

	public Phone add(Phone phone);	
	//Tim kiem phone theo key nhap vao (Tim theo ten va theo hang)
	public List<Phone> search(String key);
	
	public Phone getById(long id);
	
}
