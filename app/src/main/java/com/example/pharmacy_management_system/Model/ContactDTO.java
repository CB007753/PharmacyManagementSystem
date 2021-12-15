package com.example.pharmacy_management_system.Model;
//DTO is a design pattern conceived to reduce the number of calls when working with remote interfaces

public class ContactDTO {

    private String email;
    private String message;
    private String date;


    //Constructors
    public ContactDTO(String email, String message, String date) {
        this.email = email;
        this.message = message;
        this.date = date;
    }

    public ContactDTO() {
    }

    //getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
