package com.zhdanko.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conn {
    private static Connection connection;

    public static final String URL = "jdbc:postgresql://localhost:5432/rental_car";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "secret00";

    public static Connection GetConnection() {
        if (connection == null) {
            tryToConnectDatabase();
        }
        return connection;
    }

    private static void tryToConnectDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}