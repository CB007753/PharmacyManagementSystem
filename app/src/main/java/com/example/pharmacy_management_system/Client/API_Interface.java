package com.example.pharmacy_management_system.Client;

import com.example.pharmacy_management_system.JsonResponse.LoginRespo;
import com.example.pharmacy_management_system.Model.BuyDrugs;
import com.example.pharmacy_management_system.Model.ContactDTO;
import com.example.pharmacy_management_system.Model.Drugs;
import com.example.pharmacy_management_system.Model.Order;
import com.example.pharmacy_management_system.Model.OrderDTO;
import com.example.pharmacy_management_system.Model.RegistrationDTO;
import com.example.pharmacy_management_system.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
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

    //this will find and get all the orders with status "Delivered", this is used to display all stocks in pharmacy in home page.
    @GET("User/ordersinstock/{status}")
    Call<List<Order>> getDeliveredOrders(@Path("status")String status);

    @GET("User/onthewayorders/{email}/{status}")
    Call<List<Order>> ViewOnTheWayOrders(@Path("email")String username, @Path("status")String status);

    @GET("User/deliveredorders/{email}/{status}")
    Call<List<Order>> ViewDeliveredOrders(@Path("email")String username,@Path("status")String status);

    @POST("User/updateorder")
    Call<Void> update_Order(@Body Order order);

    //this will find and get all details of user linked with provided email. used for view profile function
    @GET("User/finduser/{email}")
    Call<User> getUserByEmail(@Path("email")String username);

    // works perfectly if id provided correctly
    @GET("User/deletedrug/{id}")
    Call<Order> DeleteOrderByID(@Path("id")Long id);

    @POST("User/addmessage")
    Call<Void> SendMessage(@Body ContactDTO contactDTO);

    //not used(previously used in Update OrderActivity)
    @HTTP(method = "DELETE", path = "User/deleteorder", hasBody = true)
    Call<Void> DeleteOrder(@Body OrderDTO orderDTO);

}
