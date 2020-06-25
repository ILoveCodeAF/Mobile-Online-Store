package com.example.nhom11.controller.admin;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Screen;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;

@WebServlet(urlPatterns = "/admin/edit-phone")
@MultipartConfig(maxFileSize = 2 * 1024 * 1024, maxRequestSize = 2 * 1024 * 1024) // Toi da 2 MB
public class EditPhoneController extends HttpServlet {

    private static final String UPLOAD_DIR = "/static/image/phone/";
    private static final long MAX_UPLOAD_SIZE = 1024 * 1024 * 2; // 2 MB

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id =  Long.parseLong(req.getParameter("id"));
        PhoneDAOTuan phoneDAO = new PhoneDAOTuanImpl();
        Phone phone = phoneDAO.getById(id);
        req.setAttribute("phone", phone);
        req.getRequestDispatcher("edit-phone.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id= Long.parseLong(req.getParameter("id"));
        long screenId = Long.parseLong(req.getParameter("screenId"));
        //Add phone Information
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
        }
        catch (Exception e) {
            Screen s = new Screen(0, technology, resolution, size);
            Phone p = new Phone(0, name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, os, battery, "", s,
                    price);
            req.setAttribute("phone", p);
            req.getRequestDispatcher("edit-phone.jsp").forward(req, resp);
        }

        Screen screen = new Screen(screenId, technology, resolution, size);
        Phone phone = new Phone(id, name, manufacturer, rom, ram, cpu, frontCamera, behindCamera, os, battery, null,
                screen, price);
        PhoneDAOTuan pd=new PhoneDAOTuanImpl();

        String result = "Success";
        Part imagePart = req.getPart("image");
        if(imagePart!=null && !imagePart.getSubmittedFileName().trim().isEmpty()){
            String filename = imagePart.getSubmittedFileName();	//Ten moi cua file dung de upload len server
            String image = UPLOAD_DIR + filename;							//Dung de luu vao DB
            String fileLocation = req.getRealPath(UPLOAD_DIR) + filename;	//Vi tri cua file tren server
            result = upload(imagePart, MAX_UPLOAD_SIZE, fileLocation);
            phone.setImage(image);
        }

        if(result.equalsIgnoreCase("Success")){
            boolean editResult = pd.update(phone);
            if(!editResult) {	//Edit that bai
                req.setAttribute("notification", "Fail");
                req.setAttribute("phone", phone);
                req.getRequestDispatcher("edit-phone.jsp").forward(req, resp);
            }
            else {									//Edit thanh cong ->
                req.setAttribute("notification", "Success");
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
}
