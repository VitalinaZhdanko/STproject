package com.zhdanko.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebFilter("/AuthFilter")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        String name = servletRequest.getParameter("name");
        String p = servletRequest.getParameter("currentPage");
        String n = servletRequest.getParameter("recordsPerPage");

        System.out.println("parametra");
        System.out.println(p);
        System.out.println(n);

        if(name.equals("admin")){
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else{
            out.print("Неверное имя");
            RequestDispatcher rd = servletRequest.getRequestDispatcher("views/auth.html");
            rd.include(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
