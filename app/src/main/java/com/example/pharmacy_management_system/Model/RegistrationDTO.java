package com.example.pharmacy_management_system.Model;

//this DTO(data transfer object) file is used for registration purpose
public class RegistrationDTO {

    private String fullname;

    private String email;

    private String password;

    private String mobile;

    //Constructors

    public RegistrationDTO(String fullname, String email, String password, String mobile) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
    }

    public RegistrationDTO() {
    }

    //Getters and Setters

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
