package com.zhdanko.entities;

import java.util.Date;

public class TechnicalInspection {
    private final int id;
    private final String number;
    private final Date dateOfIssue;
    private final int validityPeriod;

    public TechnicalInspection(int id, String number, Date dateOfIssue, int validityPeriod) {
        this.id = id;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.validityPeriod = validityPeriod;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }
}
