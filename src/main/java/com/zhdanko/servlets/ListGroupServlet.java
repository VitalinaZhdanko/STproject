package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;
import com.zhdanko.model.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListGroupServlet extends HttpServlet {
    private final Model groups = Model.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DatabaseService.getGroupList();

        req.setAttribute("groups", groups.listItem());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/listGroup.jsp");
        requestDispatcher.forward(req, resp);

        groups.removeList();

    }
}
