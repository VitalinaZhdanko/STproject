package com.zhdanko.database;

import com.zhdanko.entities.Car;
import com.zhdanko.entities.CarGroup;
import com.zhdanko.entities.Client;
import com.zhdanko.entities.DrivingLicense;
import com.zhdanko.entities.Order;
import com.zhdanko.model.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService {

    private static final Connection connection = Conn.GetConnection();

    static String queryGetStatusOrder = "SELECT name FROM status_order WHERE id = (SELECT status_order_id FROM client_order WHERE id = ?);";

    static String queryGetCar = "SELECT c.id, c.number, c.name, c.win_number, c.year_of_release, c.cost_minute, \n" +
            "br.name, st.name, tech.number, tech.date_of_issue, tech.validity_period, \n" +
            "ins.number, ins.date_of_issue, ins.validity_period FROM car AS c \n" +
            "JOIN car_brand AS br ON c.car_brand_id=br.id \n" +
            "JOIN status_car AS st ON c.status_car_id=st.id \n" +
            "JOIN technical_inspection AS tech ON c.technical_inspection_id=tech.id\n" +
            "JOIN insurance_policy AS ins ON c.insurance_policy_id = ins.id\n" +
            "WHERE c.is_available = true AND c.car_group_id = ? ;";

    static String queryGetGroup = "SELECT * FROM car_group; ";

    static String queryGetOrder = "SELECT * FROM client_order LIMIT ? OFFSET ?;";

    static String queryGetCostByCarID = "SELECT cost_minute FROM car WHERE id = ?;";

    static String queryGetClientByOrderId = "SELECT phone_number, email, fio," +
            "passport_number, identification_number, date_of_issue," +
            "validity_period, issued_by_whom, residence_address " +
            "FROM client WHERE id = (SELECT client_id FROM client_order WHERE id = ?);";

    static String queryChangeIsNotAvailableCar = "UPDATE car SET is_available = false WHERE id = ?";

    static String queryChangeIsAvailableCar = "UPDATE car SET is_available = true WHERE id = (SELECT car_id FROM client_order where id = ? )";

    static String queryAddOrder = "INSERT INTO client_order" +
            "( order_date_time, date_time_start_rent, date_time_finish_rent, " +
            "cost, client_id, car_id, manager_id, status_order_id) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

    static String queryAddDrivingLicense = "INSERT INTO driving_license" +
            "(number, category, date_of_issue, validity_period, client_id) " +
            "VALUES(?, ?, ?, ?, ?)";

    static  String queryAddClient = "INSERT INTO client" +
            "(phone_number, email, fio, passport_number, identification_number, " +
            "date_of_issue, validity_period, issued_by_whom, residence_address) " +
            "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    static String queryAddComment = "UPDATE client_order SET comment = ? WHERE id = ?;";

    static String queryGetComment = "select comment FROM client_order WHERE id = ?;";

    static String queryChangeStatus = "UPDATE client_order SET status_order_id = ? where id = ?";

    static String queryGetCountOrders = "SELECT COUNT(id) FROM client_order";

    public static void changeStatusOrder(int idStatus, int idOrder){
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryChangeStatus);
            prepStat.setInt(1, idStatus);
            prepStat.setInt(1, idOrder);

            prepStat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static String getStatusOrderById(int orderId) {
        String status = "";
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetStatusOrder);
            prepStat.setInt(1, orderId);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                status = rs.getString(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return status;
    }

    public static String getComment(int idOrder) {
        String comment = "";
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetComment);
            prepStat.setInt(1, idOrder);

            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                comment = rs.getString("comment");
            }

            rs.close();
            prepStat.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return comment;
    }

    public static void addComment(String comment, int id){
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryAddComment);
            prepStat.setString(1, comment);
            prepStat.setInt(2, id);

            prepStat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static void changeIsAvailable(int idCar) {
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryChangeIsAvailableCar);
            prepStat.setInt(1, idCar);

            prepStat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Client getClientByOrderId(int idOrder){
        Client client = null;
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetClientByOrderId);
            prepStat.setInt(1, idOrder);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                client = new Client(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getDate(6),
                        rs.getInt(7), rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return client;
    }

    public static void getCar(int idGroup) {
        Model cars = Model.getInstance();

        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetCar);
            prepStat.setInt(1, idGroup);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                cars.addItem(new Car(
                        rs.getInt(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getInt(5), rs.getDouble(6),
                        rs.getString(7), rs.getString(8), rs.getString(9),
                        rs.getDate(10), rs.getInt(11), rs.getString(12),
                        rs.getDate(13), rs.getInt(14)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void getGroupList() {
        Model groups = Model.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryGetGroup);

            while (rs.next()) {
                groups.addItem(new CarGroup(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static List<Order> getOrderList(int currentPage, int recordsPerPage) {
        List<Order> orders = new ArrayList<>();

        int start = currentPage * recordsPerPage - recordsPerPage;

        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetOrder);
            prepStat.setInt(1, recordsPerPage);
            prepStat.setInt(2, start);
            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                Order order = new Order(rs.getInt(1), rs.getString(2),
                        rs.getDate(3), rs.getDate(4),
                        rs.getDate(5), rs.getDouble(6),
                        rs.getInt(7), rs.getInt(8),
                        rs.getInt(9),rs.getInt(10));
               orders.add(order);
                System.out.println(order.getDateTimeStartRent());
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return orders;
    }

    public static int getCountOrders(){
        int number = 0;
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(queryGetCountOrders);

            while (rs.next()) {
                number = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return number;
    }

    public static int addClient(Client client) {
        Model clients = Model.getInstance();
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryAddClient, Statement.RETURN_GENERATED_KEYS);
            prepStat.setString(1, client.getPhoneNumber());
            prepStat.setString(2, client.getEmail());
            prepStat.setString(3, client.getFio());
            prepStat.setString(4, client.getPassportNumber());
            prepStat.setString(5, client.getIdentificationNumber());
            prepStat.setDate(6, new java.sql.Date(client.getDateOfIssue().getTime()));
            prepStat.setInt(7, client.getValidityPeriod());
            prepStat.setString(8, client.getIssuedByWhom());
            prepStat.setString(9, client.getResidenceAddress());

            client.setId(getId(prepStat));
            clients.addItem(client);

            prepStat.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return client.getId();
    }

    public static void addDrivingLicense(DrivingLicense license) {
        Model licenses = Model.getInstance();
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryAddDrivingLicense, Statement.RETURN_GENERATED_KEYS);
            prepStat.setString(1, license.getNumber());
            prepStat.setString(2, license.getCategory());
            prepStat.setDate(3, new java.sql.Date(license.getDateOfIssue().getTime()));
            prepStat.setInt(4, license.getValidityPeriod());
            prepStat.setInt(5, license.getClientId());

            license.setId(getId(prepStat));
            licenses.addItem(license);

            prepStat.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void addOrder(Order order) {
        Model orders = Model.getInstance();
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryAddOrder, Statement.RETURN_GENERATED_KEYS);

            prepStat.setTimestamp(1, new Timestamp(order.getOrderDateTime().getTime()));
            prepStat.setTimestamp(2, new Timestamp(order.getDateTimeStartRent().getTime()));
            prepStat.setTimestamp(3, new Timestamp(order.getDateTimeFinishRent().getTime()));
            prepStat.setDouble(4, order.getCost());
            prepStat.setInt(5, order.getClientId());
            prepStat.setInt(6, order.getCarId());
            prepStat.setInt(7, order.getManagerId());
            prepStat.setInt(8, order.getStatusOrderId());

            order.setId(getId(prepStat));
            orders.addItem(order);

            prepStat.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static int getId(PreparedStatement prepStat) {
        int rowInsert, id = 0;
        try {
            rowInsert = prepStat.executeUpdate();

            if (rowInsert == 1) {
                ResultSet result = prepStat.getGeneratedKeys();

                if (result.next()) {
                    id = result.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return id;
    }

    public static double getCostByCarId(int idCar) {
        double costMinute = 0;
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryGetCostByCarID);
            prepStat.setInt(1, idCar);

            ResultSet rs = prepStat.executeQuery();

            while (rs.next()) {
                costMinute = rs.getDouble(1);
            }

            rs.close();
            prepStat.close();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return costMinute;
    }

    public static void changeIsNotAvailable(int idCar) {
        try {
            PreparedStatement prepStat = connection.prepareStatement(queryChangeIsNotAvailableCar);
            prepStat.setInt(1, idCar);

            prepStat.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
