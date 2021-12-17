package com.example.pharmacy_management_system;

public class LoggedInUser {
    private String email;
    private String role;


    //Constructors

    public LoggedInUser(String email, String role) {
        this.email = email;
        this.role = role;
    }

    public LoggedInUser() {
    }


    //Getters and setters

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
