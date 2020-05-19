package com.example.nhom11.demo;

import com.example.nhom11.dao.AccountDAOTuan;
import com.example.nhom11.dao.impl.AccountDAO;
import com.example.nhom11.model.Account;

public class Run {
	public static void main(String[] args) {
		AccountDAOTuan ad=new AccountDAO();
		Account a=ad.login("tuan", "1234");
		System.out.println(a.toString());
	}
}
