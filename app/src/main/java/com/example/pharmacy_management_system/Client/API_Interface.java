package com.example.pharmacy_management_system.Client;

import java.util.List;

//retrofit is a popular java and android library used to make sending and retrieving data to web service easier

import retrofit2.Call;
import retrofit2.http.GET;


public interface API_Interface {

    @GET("posts")
    Call<List<API_Model>> getPosts();
}
