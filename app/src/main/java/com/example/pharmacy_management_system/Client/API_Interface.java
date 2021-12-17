package com.example.pharmacy_management_system.Client;

import com.example.pharmacy_management_system.JsonResponse.LoginRespo;
import com.example.pharmacy_management_system.Model.RegistrationDTO;

//retrofit is a popular java and android library used to make sending and retrieving data to web service easier
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface API_Interface {

    @POST("User/Register")
    Call<LoginRespo> RegisterUser(@Body RegistrationDTO registrationDTO);

    @GET("login/{username}/{password}")
    Call<LoginRespo> getLoginResponse(@Path("username")String username,
                                      @Path("password")String password);


}
