package com.zhdanko.entities;

import java.util.Date;

public class Car {
    private final int id;
    private final String number;
    private final String name;
    private final String winNumber;
    private final int yearOfRelease;
    private final double costOfMinute;
    private final String brandName;
    private final String statusName;
    private final String insuranceNumber;
    private final Date insuranceDate;
    private final int insurancePeriod;
    private final String techNumber;

    public int getId() {
        return id;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public int getInsurancePeriod() {
        return insurancePeriod;
    }

    public String getTechNumber() {
        return techNumber;
    }

    public Date getTechDate() {
        return techDate;
    }

    public int getTechPeriod() {
        return techPeriod;
    }

    private final Date techDate;
    private final int techPeriod;


    public Car(int id, String text, String name, String winNumber, int yearOfRelease, double costOfMinute, String brandName, String statusName, String insuranceNumber, Date insuranceDate, int insurancePeriod, String techNumber, Date techDate, int techPeriod) {
        this.id = id;
        this.number = text;
        this.name = name;
        this.winNumber = winNumber;
        this.yearOfRelease = yearOfRelease;
        this.costOfMinute = costOfMinute;
        this.brandName = brandName;
        this.statusName = statusName;
        this.insuranceNumber = insuranceNumber;
        this.insuranceDate = insuranceDate;
        this.insurancePeriod = insurancePeriod;
        this.techNumber = techNumber;
        this.techDate = techDate;
        this.techPeriod = techPeriod;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getWinNumber() {
        return winNumber;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    public double getCostOfMinute() {
        return costOfMinute;
    }


}
