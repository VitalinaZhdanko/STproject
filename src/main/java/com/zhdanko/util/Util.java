package com.zhdanko.util;

import com.zhdanko.database.DatabaseService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static Date convertToDate(String dateString) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return date;
    }

    public static Date convertToDateTime(String dateString) {
        Date result = null;
        String date = dateString.replace("T", " ");
        try {
            result = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse((date));
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    public static double getTotalCost(Date start, Date finish, int carId) {
        double costMinute = DatabaseService.getCostByCarId(carId);
        double cost = (finish.getTime() - start.getTime()) / 60000 * costMinute;

        return roundAvoid(cost, 2);
    }

    public static double roundAvoid(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public static boolean isValidEmail(String email){
        String pattern = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(email);

        //System.out.println(matcher.matches());
        return !matcher.matches();
    }

    public static boolean isValidPassportNumber(String passportNumber){
        String pattern = "[A-Z]{1}[0-9]{7}";
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(passportNumber);

        //System.out.println(matcher.matches());
        return !matcher.matches();
    }
}
