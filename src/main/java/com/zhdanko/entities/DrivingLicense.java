package com.zhdanko.entities;

import java.util.Date;

public class DrivingLicense {
    private int id;
    private final String number;
    private final String category;
    private final Date dateOfIssue;
    private final int validityPeriod;
    private final int clientId;

    public DrivingLicense(String number, String category, Date dateOdIssue, int validityPeriod, int clientId) {
        this.number = number;
        this.category = category;
        this.dateOfIssue = dateOdIssue;
        this.validityPeriod = validityPeriod;
        this.clientId = clientId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getCategory() {
        return category;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public int getClientId() {
        return clientId;
    }
}
