package com.example.pharmacy_management_system.Model;

public class Order {

    private int id;

    private String email;

    private String date;

    private String drugname;

    private int price;

    private int quantity;

    private String unit;

    private String status;

    private int total;


    //Constructors

    public Order(int id, String email, String date, String drugname, int price, int quantity, String unit, String status, int total) {
        this.id = id;
        this.email = email;
        this.date = date;
        this.drugname = drugname;
        this.price = price;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
        this.total = total;
    }

    public Order(int id, String drugname, int quantity, String unit, String status, int total) {
        this.id = id;
        this.drugname = drugname;
        this.quantity = quantity;
        this.unit = unit;
        this.status = status;
        this.total = total;
    }

    public Order() {
    }


    //Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setQuantity(int qnty) {
        this.quantity = qnty;
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
