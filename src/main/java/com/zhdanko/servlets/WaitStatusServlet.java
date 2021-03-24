package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WaitStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding( "UTF-8" );

        String comment = DatabaseService.getComment(Integer.parseInt(req.getParameter("idOrder")));
        String status = DatabaseService.getStatusOrderById(Integer.parseInt(req.getParameter("idOrder")));
        req.setAttribute("status", status);
        req.setAttribute("comment", comment);

        RequestDispatcher request = req.getRequestDispatcher("views/waitConfirm.jsp");
        request.forward(req, resp);
    }
}
