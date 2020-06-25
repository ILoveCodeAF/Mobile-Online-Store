package com.example.nhom11.controller.admin;

import com.example.nhom11.dao.PhoneDAOTuan;
import com.example.nhom11.dao.impl.PhoneDAOTuanImpl;
import com.example.nhom11.model.Phone;
import com.example.nhom11.model.Screen;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/admin/delete-phone")
public class DeletePhoneController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        PhoneDAOTuan phoneDAOTuan = new PhoneDAOTuanImpl();
        Phone phone = phoneDAOTuan.getById(id);
        req.setAttribute("phone", phone);
        req.getRequestDispatcher("delete-phone.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));
        long screenId = Long.parseLong(req.getParameter("screenId"));

        Screen screen = new Screen();
        screen.setId(screenId);
        Phone phone = new Phone();
        phone.setId(id);
        phone.setScreen(screen);

        PhoneDAOTuan phoneDAOTuan = new PhoneDAOTuanImpl();
        boolean result = phoneDAOTuan.delete(phone);
        if(result){ //Xoa thanh cong
            req.setAttribute("notification", "Success");
            req.getRequestDispatcher("notification.jsp").forward(req, resp);
        }
        else{       //Xoa that bai
            phone = phoneDAOTuan.getById(id);
            req.setAttribute("phone", phone);
            req.setAttribute("notification", "Fail");
            req.getRequestDispatcher("delete-phone").forward(req, resp);
        }

    }
}
