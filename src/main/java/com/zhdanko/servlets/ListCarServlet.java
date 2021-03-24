package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;
import com.zhdanko.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListCarServlet extends HttpServlet {
    private Model cars = Model.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idGroup = Integer.parseInt(req.getParameter("id"));
        DatabaseService.getCar(idGroup);

        req.setAttribute("cars", cars.listItem());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listCar.jsp");
        requestDispatcher.forward(req, resp);

        cars.removeList();
    }
}
