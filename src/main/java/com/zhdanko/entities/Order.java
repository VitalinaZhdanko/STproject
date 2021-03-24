package com.zhdanko.entities;

import java.util.Date;

public class Order {
    private int id;
    private String comment;
    private Date orderDateTime;
    private Date dateTimeStartRent;
    private Date dateTimeFinishRent;
    private double cost;
    private int clientId;
    private int carId;
    private int managerId;
    private int statusOrderId;

    public Order() {

    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public void setOrderDateTime(Date orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public void setDateTimeStartRent(Date dateTimeStartRent) {
        this.dateTimeStartRent = dateTimeStartRent;
    }

    public void setDateTimeFinishRent(Date dateTimeFinishRent) {
        this.dateTimeFinishRent = dateTimeFinishRent;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public Date getOrderDateTime() {
        return orderDateTime;
    }
    public void setCost(double cost) {
        this.cost = cost;
    }

    public Date getDateTimeStartRent() {
        return dateTimeStartRent;
    }

    public Date getDateTimeFinishRent() {
        return dateTimeFinishRent;
    }

    public double getCost() {
        return cost;
    }

    public int getClientId() {
        return clientId;
    }

    public int getCarId() {
        return carId;
    }

    public int getManagerId() {
        return managerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getStatusOrderId() {
        return statusOrderId;
    }

    public void setStatusOrderId(int statusOrderId) {
        this.statusOrderId = statusOrderId;
    }

    public Order(int id, String comment, Date orderDateTime, Date dateTimeStartRent, Date dateTimeFinishRent, double cost, int clientId, int carId, int managerId, int statusOrderId) {
        this.id = id;
        this.comment = comment;
        this.orderDateTime = orderDateTime;
        this.dateTimeStartRent = dateTimeStartRent;
        this.dateTimeFinishRent = dateTimeFinishRent;
        this.cost = cost;
        this.clientId = clientId;
        this.carId = carId;
        this.managerId = managerId;
        this.statusOrderId = statusOrderId;
    }
}
