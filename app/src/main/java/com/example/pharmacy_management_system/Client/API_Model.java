package com.example.pharmacy_management_system.Client;

import com.google.gson.annotations.SerializedName;

//retrofit is a popular java and android library used to make sending and retrieving data to web service easier
public class API_Model {

    private int userId;

    private int id;

    private String title;

    @SerializedName("body")
    private String text;



    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }
}
