package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;
import com.zhdanko.entities.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        session.setAttribute("name", req.getParameter("name"));

        int currentPage = Integer.parseInt(req.getParameter("currentPage"));
        int recordsPerPage = Integer.parseInt(req.getParameter("recordsPerPage"));


        List<Order> orders = DatabaseService.getOrderList(currentPage, recordsPerPage);
        req.setAttribute("orders", orders);

        for(int i = 0; i<orders.size(); i++){
            System.out.println(orders.get(i).getCost());
        }
        System.out.println(orders);

        int rows = DatabaseService.getCountOrders();
        System.out.println(rows);
        int nOfPages = rows / recordsPerPage;

        if (nOfPages % recordsPerPage > 0) {

            nOfPages++;
        }

        req.setAttribute("nOfPages", nOfPages);
        req.setAttribute("currentPage", currentPage);
        req.setAttribute("recordsPerPage", recordsPerPage);


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/orderList.jsp");
        requestDispatcher.forward(req, resp);
    }

}
