package com.example.pharmacy_management_system.Model;

import java.io.Serializable;

//this DTO(data transfer object) file is used for buying drugs purpose
public class OrderDTO implements Serializable {

    private String email;

    private String date;

    private String drugname;

    private int price;

    private int quantity;

    private String unit;

    private String status;

    private int total;


    //Constructors

    public OrderDTO(String email, String date, String drugname, int price, int quantity, String unit, String status, int total) {
        this.email = email;
        this.date = date;
        this.drugname = drugname;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
        this.total = total;
    }

    public OrderDTO() {
    }

    public OrderDTO(String date, String drugname, String email, int price, int quantity, String status, int total, String unit) {
        this.date = date;
        this.drugname = drugname;
        this.email = email;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.total = total;
        this.unit = unit;
    }


    //Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDrugname() {
        return drugname;
    }

    public void setDrugname(String drugname) {
        this.drugname = drugname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
