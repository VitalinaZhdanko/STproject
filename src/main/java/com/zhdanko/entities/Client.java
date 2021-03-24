package com.zhdanko.entities;

import java.util.Date;

public class Client {
    private int id;
    private final String phoneNumber;
    private final String email;
    private final String fio;
    private final String passportNumber;
    private final String identificationNumber;
    private final Date dateOfIssue;
    private final int validityPeriod;
    private final String issuedByWhom;

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getFio() {
        return fio;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public String getIssuedByWhom() {
        return issuedByWhom;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }


    public Client(String phoneNumber, String email, String fio, String passportNumber, String identificationNumber, Date dateOfIssue, int validityPeriod, String issuedByWhom, String residenceAddress) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.fio = fio;
        this.passportNumber = passportNumber;
        this.identificationNumber = identificationNumber;
        this.dateOfIssue = dateOfIssue;
        this.validityPeriod = validityPeriod;
        this.issuedByWhom = issuedByWhom;
        this.residenceAddress = residenceAddress;
    }

    private final String residenceAddress;
}
