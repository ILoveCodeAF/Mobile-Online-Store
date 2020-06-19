package com.example.nhom11.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Screen;
import com.example.nhom11.utils.ConnectionPool;

public class PhoneDAOTuanImpl implements PhoneDAOTuan {

	@Override
	public Phone add(Phone phone) {
		String sql = "INSERT INTO phone(name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, os, "
				+ "battery, image, price, screen_id) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";		
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
		}
		
		try {
			//Them man hinh vao DB
			phone.setScreen(addScreen(phone.getScreen(), con));
			
			ps=con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, phone.getName());
			ps.setString(2, phone.getManufacturer());
			ps.setInt(3, phone.getRom());
			ps.setInt(4, phone.getRam());
			ps.setString(5, phone.getCpu());
			ps.setFloat(6, phone.getFrontCamera());
			ps.setFloat(7, phone.getBehindCamera());
			ps.setString(8, phone.getOs());
			ps.setInt(9, phone.getBattery());
			ps.setString(10, phone.getImage());
			ps.setFloat(11, phone.getPrice());
			ps.setLong(12, phone.getScreen().getId());
			
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				phone.setId(rs.getLong(1));
			}
			
			con.commit();
			
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
			}
		}
		finally {
			pool.closeConnection(con);
		}
		
		return phone;
	}
	
	
	//Them Screen vao DB, neu neu them duoc se tra lai screen co ID la ID moi them (lon hon 0)
	private Screen addScreen(Screen screen, Connection con) {
		String sql = "INSERT INTO screen(size, technology, resolution) VALUES(?,?,?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
		
		try {
			ps=con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setFloat(1, screen.getSize());
			ps.setString(2, screen.getTechnology());
			ps.setString(3, screen.getResolution());
			
			ps.executeUpdate();
			
			rs=ps.getGeneratedKeys();
			if(rs.next()) {
				screen.setId(rs.getLong(1));
			}			
		} catch (SQLException e) {
		}
		
		return screen;
	}


	@Override
	public List<Phone> search(String key) {
		String keySearch = "%"+key+"%";
		List<Phone> phones=new ArrayList<>();
		
		String sql = "SELECT phone.id as id, name, manufacturer, rom , ram, cpu,  os, image, price, size, resolution, technology "
				+ "FROM phone, screen "
				+ "WHERE phone.screen_id=screen.id AND "
				+ "(phone.name LIKE ? OR phone.manufacturer LIKE ?)";
		PreparedStatement ps=null;
		ResultSet rs=null;
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, keySearch);
			ps.setString(2, keySearch);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Phone p=new Phone();
				Screen s=new Screen();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setRom(rs.getInt("rom"));
				p.setRam(rs.getInt("ram"));
				p.setCpu(rs.getString("cpu"));
				p.setOs(rs.getString("os"));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getFloat("price"));
				s.setSize(rs.getFloat("size"));
				s.setResolution(rs.getString("resolution"));
				s.setTechnology(rs.getString("technology"));
				p.setScreen(s);
				phones.add(p);				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
			
		return phones;
	}
	

	@Override
	public Phone getById(long id) {
		String sql = "SELECT phone.id as id, name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, "
				+ "os, battery, image, price, size, technology, resolution "
				+ "FROM phone, screen "
				+ "WHERE phone.screen_id = screen.id AND phone.id = ?";
		Phone p=null;
		Screen s=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setLong(1, id);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				p=new Phone();
				s=new Screen();
				
				s.setSize(rs.getFloat("size"));
				s.setTechnology(rs.getString("technology"));
				s.setResolution(rs.getString("resolution"));
				
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setRom(rs.getInt("rom"));
				p.setRam(rs.getInt("ram"));
				p.setCpu(rs.getString("cpu"));
				p.setFrontCamera(rs.getFloat("frontCamera"));
				p.setBehindCamera(rs.getFloat("behindCamera"));
				p.setOs(rs.getString("os"));
				p.setBattery(rs.getInt("battery"));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getFloat("price"));
				p.setScreen(s);
			}
			
		} catch (SQLException e) {
		}
		finally {
			pool.closeConnection(con);
		}
		
		
		return p;
		
	}


	@Override
	public List<Phone> searchWithPagination(String key, int page, int eachPage) {
		int from = (page-1)*eachPage;
		int to = from +10;
		String keySearch = "%"+key+"%";
		List<Phone> phones=new ArrayList<>();
		
		String sql = "SELECT phone.id as id, name, manufacturer, rom , ram, cpu,  os, image, price, size, resolution, technology "
				+ "FROM phone, screen "
				+ "WHERE phone.screen_id=screen.id AND "
				+ "(phone.name LIKE ? OR phone.manufacturer LIKE ?) "
				+ "LIMIT ?, ?";
		PreparedStatement ps=null;
		ResultSet rs=null;
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, keySearch);
			ps.setString(2, keySearch);
			ps.setInt(3, from);
			ps.setInt(4, to);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Phone p=new Phone();
				Screen s=new Screen();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setRom(rs.getInt("rom"));
				p.setRam(rs.getInt("ram"));
				p.setCpu(rs.getString("cpu"));
				p.setOs(rs.getString("os"));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getFloat("price"));
				s.setSize(rs.getFloat("size"));
				s.setResolution(rs.getString("resolution"));
				s.setTechnology(rs.getString("technology"));
				p.setScreen(s);
				phones.add(p);				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
				
		return phones;
	}


	@Override
	public List<Phone> getNewestPhones(int quantity) {
		List<Phone> phones=new ArrayList<>();
		
		String sql = "SELECT phone.id as id, name, manufacturer, rom , ram, cpu,  os, image, price, size, resolution, technology "
				+ "FROM phone, screen "
				+ "WHERE phone.screen_id=screen.id "
				+ "ORDER BY id DESC "
				+ "LIMIT ?, ?";
		PreparedStatement ps=null;
		ResultSet rs=null;
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, 0);
			ps.setInt(2, quantity);
			
			rs=ps.executeQuery();
			while(rs.next()) {
				Phone p=new Phone();
				Screen s=new Screen();
				p.setId(rs.getLong("id"));
				p.setName(rs.getString("name"));
				p.setManufacturer(rs.getString("manufacturer"));
				p.setRom(rs.getInt("rom"));
				p.setRam(rs.getInt("ram"));
				p.setCpu(rs.getString("cpu"));
				p.setOs(rs.getString("os"));
				p.setImage(rs.getString("image"));
				p.setPrice(rs.getFloat("price"));
				s.setSize(rs.getFloat("size"));
				s.setResolution(rs.getString("resolution"));
				s.setTechnology(rs.getString("technology"));
				p.setScreen(s);
				phones.add(p);				
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			pool.closeConnection(con);
		}
				
		return phones;
	}

	@Override
	public int getTotalPage(String key, int eachPage) {
		int count=0;
		String keySearch = "%"+key+"%";
		String sql = "SELECT COUNT(id) FROM phone "
				+ "WHERE name LIKE ? OR manufacturer LIKE ?";
		PreparedStatement ps=null;
		ResultSet rs=null;
//		Connection con=DBUtil.getConnection();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection con=pool.getConnection();
		
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, keySearch);
			ps.setString(2, keySearch);
			
			rs=ps.executeQuery();
			if(rs.next()) {
				
				int total = rs.getInt(1);
				if(total%eachPage==0) count = total/eachPage;
				else count = total/eachPage+1;
			}
			
		} catch (SQLException e) {
		}
		finally {
			pool.closeConnection(con);
		}
		
		
		return count;
	}
	

}
