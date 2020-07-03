package com.example.nhom11.demo;

import com.example.nhom11.dao.impl.OrderDAOTuanImpl;

public class Run {
	public static void main(String[] args) {
//		Cart c=new Cart();
//		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
//		c.addPhone(new PhoneSelling(0, pd.getById(1), 1));
//		c.addPhone(new PhoneSelling(0, pd.getById(18), 1));
//		c.addPhone(new PhoneSelling(0, pd.getById(17), 1));
//		c.addPhone(new PhoneSelling(0, pd.getById(17), 1));
//		c.addPhone(new PhoneSelling(0, pd.getById(18), 1));
//		for(PhoneSelling p:c.getPhones()) {
//			System.out.println(p.toString());
//		}
		
//		System.out.println(new Long(null));
		
		System.out.println(new OrderDAOTuanImpl().viewDetail(3).toString());
	}
}
