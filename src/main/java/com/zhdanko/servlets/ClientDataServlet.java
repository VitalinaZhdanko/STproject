package com.zhdanko.servlets;

import com.zhdanko.database.DatabaseService;
import com.zhdanko.entities.Client;
import com.zhdanko.entities.DrivingLicense;
import com.zhdanko.entities.Order;
import com.zhdanko.model.Model;
import com.zhdanko.util.Util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ClientDataServlet extends HttpServlet {
    private Model orders = Model.getInstance();

    Order order = new Order();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idCar = Integer.parseInt(req.getParameter("id"));
        DatabaseService.changeIsNotAvailable(idCar);

        order.setCarId(idCar);
        order.setOrderDateTime(new Date());

        RequestDispatcher request = req.getRequestDispatcher("views/clientData.jsp");
        request.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fio = req.getParameter("fio");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");
        String passportNumber = req.getParameter("passportNumber");
        String identificationNumber = req.getParameter("identificationNumber");
        Date dateOfIssue = Util.convertToDate(req.getParameter("dateOfIssue"));
        int validityPeriod = Integer.parseInt(req.getParameter("validityPeriod"));
        String issuedByWhom = req.getParameter("issuedByWhom");
        String residenceAddress = req.getParameter("residenceAddress");
        String licenseNumber = req.getParameter("licenseNumber");
        String licenseCategory = req.getParameter("licenseCategory");
        Date licenseDateOfIssue = Util.convertToDate(req.getParameter("licenseDateOfIssue"));
        int licenseValidityPeriod = Integer.parseInt(req.getParameter("licenseValidityPeriod"));
        Date dateStartRent = Util.convertToDateTime(req.getParameter("date_time_start_rent"));
        Date dateFinishRent = Util.convertToDateTime(req.getParameter("date_time_finish_rent"));


        if (fio.isEmpty() || phone.isEmpty() || email.isEmpty() ||
                passportNumber.isEmpty() || identificationNumber.isEmpty() ||
                issuedByWhom.isEmpty() || residenceAddress.isEmpty() ||
                licenseNumber.isEmpty() || licenseCategory.isEmpty()) {

            RequestDispatcher request = req.getRequestDispatcher("views/clientData.jsp");
            request.include(req, resp);

        } else {
            Client client = new Client(phone, email, fio, passportNumber, identificationNumber, dateOfIssue, validityPeriod, issuedByWhom, residenceAddress);

            int idClient = DatabaseService.addClient(client);
            order.setClientId(idClient);
            order.setDateTimeStartRent(dateStartRent);
            order.setDateTimeFinishRent(dateFinishRent);
            order.setCost(Util.getTotalCost(dateStartRent, dateFinishRent, order.getCarId()));
            order.setManagerId(1);
            order.setStatusOrderId(1);

            DatabaseService.addOrder(order);
            orders.addItem(order);

            DrivingLicense license = new DrivingLicense(licenseNumber, licenseNumber, licenseDateOfIssue, licenseValidityPeriod, idClient);
            DatabaseService.addDrivingLicense(license);

            String path = req.getContextPath() + "/waitConfirm" + "?idOrder=" + order.getId();
            resp.sendRedirect(path);
        }
    }


}
