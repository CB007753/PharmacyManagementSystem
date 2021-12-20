package com.example.pharmacy_management_system.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//this model will be used to access the drugs which can be bought by the pharmacist.
public class BuyDrugs {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("unit")
    @Expose
    private String unit;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("price")
    @Expose
    private int price;


    //Constructors

    public BuyDrugs(String id, String name, String unit, String description, int price) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.description = description;
        this.price = price;
    }

    public BuyDrugs(String name, String unit, int price) {
        this.name = name;
        this.unit = unit;
        this.price = price;
    }

    public BuyDrugs() {
    }

    //Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
