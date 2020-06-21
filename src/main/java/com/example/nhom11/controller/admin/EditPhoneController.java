package com.example.nhom11.controller.admin;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

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

@WebServlet(value = "/admin/edit-phone")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 1024 * 1024) // Toi da 2 MB
public class EditPhoneController extends HttpServlet {

	private static final String UPLOAD_DIR = "/static/image/phone/";
	private static final long MAX_UPLOAD_SIZE = 1024 * 1024 * 2; // 2 MB

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("---------doget-------------------------"+req.getContextPath());
		String str_id = req.getParameter("id");
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		
		Phone phone = pd.getById(Integer.parseInt(str_id));
		req.setAttribute("phone", phone);
		req.getRequestDispatcher("/admin/edit-phone.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("===================do post");
		long id = Long.parseLong(req.getParameter("id"));
		
		PhoneDAOTuan pd=new PhoneDAOTuanImpl();
		Phone phone = pd.getById(id);
		
		String name = req.getParameter("name");
		if (name != null && name != "")
			phone.setName(name);
		
		String manufacturer = req.getParameter("manufacturer");
		if(manufacturer != null && manufacturer != "")
			phone.setManufacturer(manufacturer);
		
		String os = req.getParameter("os");
		if(os != null && os != "")
			phone.setOs(os);
		
		String cpu = req.getParameter("cpu");
		if(cpu != null && cpu != "")
			phone.setCpu(cpu);
		
		String technology = req.getParameter("screen.technology");
		if(technology != null && technology != "")
			phone.getScreen().setTechnology(technology);
		
		String resolution = req.getParameter("screen.resolution");
		if(resolution != null && resolution != "")
			phone.getScreen().setResolution(resolution);
		
		int rom = 0, ram = 0, battery = 0;
		float frontCamera = 0, behindCamera = 0, price = 0, size = 0;
		try {
			rom = Integer.parseInt(req.getParameter("rom"));
			phone.setRom(rom);
			
			ram = Integer.parseInt(req.getParameter("ram"));
			phone.setRam(ram);
			
			frontCamera = Float.parseFloat(req.getParameter("frontCamera"));
			phone.setFrontCamera(frontCamera);
			
			behindCamera = Float.parseFloat(req.getParameter("behindCamera"));
			phone.setBehindCamera(behindCamera);
			
			price = Float.parseFloat(req.getParameter("price"));
			phone.setPrice(price);
			
			battery = Integer.parseInt(req.getParameter("battery"));
			phone.setBattery(battery);
			
			size = Float.parseFloat(req.getParameter("screen.size"));
			phone.getScreen().setSize(size);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("phone", phone);
			req.getRequestDispatcher("edit-phone.jsp").forward(req, resp);
		}

		String result = "";
		Part imagePart = req.getPart("image");
		if(imagePart != null) {
			String filename = getNewFileName(imagePart.getSubmittedFileName());	//Ten moi cua file dung de upload len server
			String image = UPLOAD_DIR + filename;							//Dung de luu vao DB
			if(filename != null && filename != "") {
				String fileLocation = req.getRealPath(UPLOAD_DIR) + filename;	//Vi tri cua file tren server
				result = upload(imagePart, MAX_UPLOAD_SIZE, fileLocation);
				phone.setImage(image);
			}
		}
		System.out.println("updating...");
		boolean success = pd.update(phone);
		System.out.println("ret_Id: " + success);
		if(!success) {	//Update that bai
			req.setAttribute("phone", phone);
			PrintWriter out = resp.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Updated phone fail');");
			out.println("location='edit-phone.jsp'");
			out.println("</script>");
		}
		else {									//update thanh cong ->
			System.out.println(req.getContextPath());
			
			PrintWriter out = resp.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Updated phone successfully');");
			out.println(String.format("location='%s/admin/search-phone'", req.getContextPath()));
			out.println("</script>");
			
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
		if (originalFileName.contains("."))
			return System.currentTimeMillis()+originalFileName.substring(originalFileName.lastIndexOf("."));
		return originalFileName;
	}

}
