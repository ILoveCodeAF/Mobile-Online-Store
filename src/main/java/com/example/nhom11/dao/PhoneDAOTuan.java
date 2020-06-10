package com.example.nhom11.dao;

import java.util.List;

import com.example.nhom11.model.Phone;

public interface PhoneDAOTuan {

	public Phone add(Phone phone);	
	//Tim kiem phone theo key nhap vao (Tim theo ten va theo hang) //Dung cho Admin
	public List<Phone> search(String key);	
	//Moi trang se hien thi 10 san pham
	//Tra ve danh sach Phone o trang thu page
	public List<Phone> searchWithPagination(String key, int page, int eachPage);
	//Tra ve tong so luong tran
	//Voi key va kich thuoc moi trang co san
	public int getTotalPage(String key, int eachPage);
	public List<Phone> getNewestPhones(int quantity);
	
	public Phone getById(long id);
	
}
