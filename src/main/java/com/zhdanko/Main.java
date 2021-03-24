package com.zhdanko;

import com.zhdanko.database.DatabaseService;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        DatabaseService cr = new DatabaseService();
 //       cr.getStatusOrder();
//        List<Car> cars = cr.getCar();
//        for(int i = 0; i<cars.size(); i++){
//           System.out.println(cars.get(i).getBrandName());
//        }
        System.out.println("Привет");

        Date date = new Date();
        System.out.println(date);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(dateFormatter.format(date));

        //preparedStatement.setDate(6, (java.sql.Date) client.getDateOfIssue());

    }
}
