package com.example.pharmacy_management_system.Client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Backend_API {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){

        if(retrofit==null){
            Gson gson=new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit=new Retrofit.Builder()
                    .baseUrl("http://localhost:8080/api/mobile/")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }

        return retrofit;
    }
}
