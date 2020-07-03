package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.example.nhom11.dao.OrderDAOTuan;
import com.example.nhom11.model.Cart;
import com.example.nhom11.model.Customer;
import com.example.nhom11.model.Order;
import com.example.nhom11.model.PaymentType;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.PhoneSelling;
import com.example.nhom11.model.ReceivingType;
import com.example.nhom11.model.Shipment;
import com.example.nhom11.utils.ConnectionPool;

public class OrderDAOTuanImpl implements OrderDAOTuan {

	@Override
	public Order order(Order order) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = pool.getConnection();

		String sql = "";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con.setAutoCommit(false);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		try {
			order.setCart(addCart(order.getCart(), con));
			addPhoneSelling(order.getCart(), con);
//			 Shipping
			if (order.getReceivingType() == ReceivingType.SHIPPING) {
				sql = "INSERT INTO orders (date, receivingtype, paymenttype, cart_id, shipment_id) "
						+"VALUES(?,?,?,?,?)";
			} else {
				sql = "INSERT INTO orders (date, receivingtype, paymenttype, cart_id) " 
						+ "VALUES(?,?,?,?)";
			}

			ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setDate(1, new Date(order.getDate().getTime()));
			ps.setString(2, order.getReceivingType().toString());
			ps.setString(3, order.getPaymentType().toString());
			ps.setLong(4, order.getCart().getId());			
			if(order.getReceivingType()==ReceivingType.SHIPPING) {
				order.setShipment(addShipment(order.getShipment(), con));
				ps.setLong(5, order.getShipment().getId());
			}			

			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order.setId(rs.getLong(1));
			}

			con.commit();

		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			pool.closeConnection(con);
		}

		return order;
	}
	//Add Cart
	private Cart addCart(Cart cart, Connection con) {
		String sql = "INSERT INTO cart(customer_id) " + "VALUES(?)";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, cart.getCustomer().getId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				cart.setId(rs.getLong(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cart;

	}
	// Add PhoneSelling into Cart
	private void addPhoneSelling(Cart cart, Connection con) {
		String sql = "INSERT INTO phoneselling (phone_id, quantity, cart_id) " + "VALUES(?,?,?)";
		PreparedStatement ps = null;
		try {
			for (PhoneSelling phone : cart.getPhones()) {
				ps = con.prepareStatement(sql);
				ps.setLong(1, phone.getPhone().getId());
				ps.setInt(2, phone.getQuantity());
				ps.setLong(3, cart.getId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//Add Shipment
	private Shipment addShipment(Shipment shipment, Connection con) {
		String sql = "INSERT INTO shipment (address, phone, price) " + "VALUES(?,?,?)";
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, shipment.getAddress());
			ps.setString(2, shipment.getPhone());
			ps.setFloat(3, shipment.getPrice());

			ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				shipment.setId(rs.getLong(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shipment;
	}

	@Override
	public List<Order> viewHistory(Customer customer) {
		
		List<Order> orders = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = pool.getConnection();
		String sql = "select * from "
				+ "(select orders.id as id, orders.date as date, sum(phone.price*phoneselling.quantity)+shipment.price as price " + 
				"from orders, cart, phoneselling, phone, customer, shipment " + 
				"where orders.cart_id=cart.id " + 
				"	AND orders.shipment_id IS NOT NULL " + 
				"	AND shipment.id = orders.shipment_id " + 
				"	AND cart.id = phoneselling.cart_id " + 
				"	AND phoneselling.phone_id = phone.id " + 
				"   AND cart.customer_id=customer.id " + 
				"   AND customer.id=? " + 
				"group by orders.id " + 
				"union " + 
				"select orders.id as id, orders.date as date, sum(phone.price*phoneselling.quantity) as price " + 
				"from orders, cart, phoneselling, phone, customer " + 
				"where orders.cart_id=cart.id " + 
				"	AND orders.shipment_id IS NULL " + 
				"	AND cart.id = phoneselling.cart_id " + 
				"	AND phoneselling.phone_id = phone.id " + 
				"   AND cart.customer_id=customer.id " + 
				"   AND customer.id=? " + 
				"group by orders.id) as hehe_tuandz "
				+ "order by id desc";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, customer.getId());
			ps.setLong(2, customer.getId());
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Order order = new Order();
				order.setId(rs.getLong("id"));
				order.setDate(new java.util.Date(rs.getDate("date").getTime()));
				order.setPrice(rs.getFloat("price"));
				orders.add(order);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
		
		
		return orders;
	}

	@Override
	public Order viewDetail(long id) {
		Order order = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con = pool.getConnection();
		String sql = "SELECT DISTINCT orders.id as orderId, orders.date as orderDate, orders.receivingtype, paymenttype, shipment_id, shipment.address, shipment.price as shippingPrice, " + 
				"phoneselling.quantity, phone.id as phoneId, phone.image, phone.name, phone.rom, phone.ram, phone.price as phonePrice " + 
				"FROM orders, cart, shipment, phoneselling, phone, customer " + 
				"WHERE orders.shipment_id = shipment.id " + 
				"	AND orders.cart_id = cart.id " + 
				"   AND phoneselling.cart_id = cart.id " + 
				"   AND phoneselling.phone_id = phone.id " + 
				"   AND cart.customer_id = customer.id " + 
				"	AND orders.id = ? " + 
				"UNION " + 
				"SELECT DISTINCT orders.id as orderId, orders.date as orderDate, orders.receivingtype, paymenttype, shipment_id, NULL as address, 0 as shippingPrice, " + 
				"phoneselling.quantity, phone.id as phoneId, phone.image, phone.name, phone.rom, phone.ram, phone.price as phonePrice " + 
				"FROM orders, cart, shipment, phoneselling, phone, customer " + 
				"WHERE orders.shipment_id IS NULL " + 
				"	AND orders.cart_id = cart.id " + 
				"   AND phoneselling.cart_id = cart.id " + 
				"   AND phoneselling.phone_id = phone.id " + 
				"   AND cart.customer_id = customer.id " + 
				"   AND orders.id = ? ";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement(sql);
			ps.setLong(1, id);
			ps.setLong(2, id);
			
			rs = ps.executeQuery();
			while(rs.next()) {
				//Khoi tao thong tin trong Order
				if(order==null) {
					order = new Order();
					order.setId(rs.getLong("orderId"));
					order.setDate(new java.util.Date(rs.getDate("orderDate").getTime()));
					order.setReceivingType(ReceivingType.valueOf(rs.getString("receivingtype")));
					order.setPaymentType(PaymentType.valueOf(rs.getString("paymenttype")));
					
					Shipment shipment = null;
					Cart cart = new Cart(0, null, new HashSet<PhoneSelling>());
					order.setCart(cart);
					
					try {
						long shipmentId = rs.getLong("shipment_id");
						shipment = new Shipment();
						shipment.setId(shipmentId);
						shipment.setAddress(rs.getString("address"));
						shipment.setPrice(rs.getFloat("shippingPrice"));
						
					} catch (Exception e) {
						
					}
					order.setShipment(shipment);
				}
				
				Phone p = new Phone();
				p.setId(rs.getLong("phoneId"));
				p.setName(rs.getString("name"));
				p.setRom(rs.getInt("rom"));
				p.setRam(rs.getInt("ram"));
				p.setPrice(rs.getFloat("phonePrice"));
				p.setImage(rs.getString("image"));
				PhoneSelling phoneSelling = new PhoneSelling(0, p, rs.getInt("quantity"));
				order.getCart().getPhones().add(phoneSelling);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
		
		return order;
	}

}
