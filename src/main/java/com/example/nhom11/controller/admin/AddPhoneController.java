package com.example.nhom11.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Screen;

@WebServlet(value = "/admin/add-phone")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 1024 * 1024) // Toi da 2 MB
public class AddPhoneController extends HttpServlet {

	private static final String UPLOAD_DIR = "/static/image/phone/";
	private static final long MAX_UPLOAD_SIZE = 1024 * 1024 * 2; // 2 MB

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("phone", new Phone());
		req.getRequestDispatcher("add-phone.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name = req.getParameter("name");
		String manufacturer = req.getParameter("manufacturer");
		String os = req.getParameter("os");
		String cpu = req.getParameter("cpu");
		String technology = req.getParameter("screen.technology");
		String resolution = req.getParameter("screen.resolution");
		int rom = 0, ram = 0, battery = 0;
		float frontCamera = 0, behindCamera = 0, price = 0, size = 0;
		try {
			rom = Integer.parseInt(req.getParameter("rom"));
			ram = Integer.parseInt(req.getParameter("ram"));
			frontCamera = Float.parseFloat(req.getParameter("frontCamera"));
			behindCamera = Float.parseFloat(req.getParameter("behindCamera"));
			price = Float.parseFloat(req.getParameter("price"));
			battery = Integer.parseInt(req.getParameter("battery"));
			size = Float.parseFloat(req.getParameter("screen.size"));
		} catch (Exception e) {
			Screen s = new Screen(0, technology, resolution, size);
			Phone p = new Phone(0, name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, os, battery, "", s,
					price);
			req.setAttribute("phone", p);
			req.getRequestDispatcher("add-phone.jsp").forward(req, resp);
		}

		Part imagePart = req.getPart("image");
		String filename = getNewFileName(imagePart.getSubmittedFileName());	//Ten moi cua file dung de upload len server
		String image = UPLOAD_DIR + filename;							//Dung de luu vao DB
		String fileLocation = req.getRealPath(UPLOAD_DIR) + filename;	//Vi tri cua file tren server

		Screen screen = new Screen(0, technology, resolution, size);
		Phone phone = new Phone(0, name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, os, battery, image,
				screen, price);
		
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		String result = upload(imagePart, MAX_UPLOAD_SIZE, fileLocation);
		if(result.equalsIgnoreCase("Success")){
			phone = pd.add(phone);
			if(phone==null || phone.getId()<=0) {	//Them that bai
				req.setAttribute("notification", "Fail");
				req.setAttribute("phone", phone);
				req.getRequestDispatcher("add-phone.jsp").forward(req, resp);
			}
			else {									//Them thanh cong ->
				req.setAttribute("notification", result);
				req.getRequestDispatcher("notification.jsp").forward(req, resp);
			}
		}
	}

	public String upload(Part part, long maxUploadSize, String fileLocation) {
		String notification = "Fail";

		if (part == null || part.getSize() == 0) {
			notification = "You haven't select any File";
		} else if (part.getSize() > maxUploadSize) {
			notification = "File break the Upload size limit";
		} else {
			try {
				// Doc file
				BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
				byte[] data = new byte[(int) part.getSize()];
				bis.read(data);
				// Ghi File
				File file = new File(fileLocation);
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				bos.write(data);

				bos.close();
				bis.close();

				notification = "Success";

			} catch (IOException e) {
				notification = "Fail";
			}
		}

		return notification;
	}

	private String getNewFileName(String originalFileName) {
		return System.currentTimeMillis() + originalFileName.substring(originalFileName.lastIndexOf("."));
	}

}
