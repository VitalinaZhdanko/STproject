package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;
import com.zhdanko.entities.Client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ManagerConfirmClient extends HttpServlet {
    int idOrder = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");

        if (name.equals("admin")){
            idOrder = Integer.parseInt(req.getParameter("id"));
            System.out.println(idOrder);
            Client client = DatabaseService.getClientByOrderId(idOrder);
            System.out.println(client.getFio());
            req.setAttribute("client", client);
            req.setAttribute("idOrder", idOrder);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/confirmClientData.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String name = (String) session.getAttribute("name");

        if (name.equals("admin")){
            String act = req.getParameter("act");
            String comment = req.getParameter("comment");


            if ("cancel".equals(act)) {
                DatabaseService.addComment(comment, idOrder);
                DatabaseService.changeIsAvailable(idOrder);
                DatabaseService.changeStatusOrder(3, idOrder);

            }

            if ("submit".equals(act)) {
                DatabaseService.addComment(comment, idOrder);
                DatabaseService.changeStatusOrder(3, idOrder);

            }
            resp.sendRedirect("/admin?name="+ name + "&currentPage=1&recordsPerPage=3");

        }

    }
}
