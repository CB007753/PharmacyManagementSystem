package com.example.pharmacy_management_system.Client;

import com.example.pharmacy_management_system.JsonResponse.LoginRespo;
import com.example.pharmacy_management_system.Model.BuyDrugs;
import com.example.pharmacy_management_system.Model.Drugs;
import com.example.pharmacy_management_system.Model.OrderDTO;
import com.example.pharmacy_management_system.Model.RegistrationDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

//retrofit is a popular java and android library used to make sending and retrieving data to web service easier
public interface API_Interface {

    @POST("User/Register")
    Call<LoginRespo> RegisterUser(@Body RegistrationDTO registrationDTO);

    @GET("login/{username}/{password}")
    Call<LoginRespo> getLoginResponse(@Path("username")String username,
                                      @Path("password")String password);

    @GET("User/druglist")
    Call<List<Drugs>> getAllDrugs();

    @GET("User/buydruglist")
    Call<List<BuyDrugs>> getBuyDrugs();

    @POST("User/placeorder")
    Call<Void> PlaceOrder(@Body OrderDTO orderDTO);


}
