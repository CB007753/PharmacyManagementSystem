package com.example.pharmacy_management_system.JsonResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRespo {

    @SerializedName("Response")
    @Expose
    private String response;


    //getters and setters

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
