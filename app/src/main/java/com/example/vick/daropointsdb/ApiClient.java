package com.example.vick.daropointsdb;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    public static final String BASE_URL = "http://192.168.43.224/daropoints/";
    public static Retrofit  RETROFIT = null;

    public static Retrofit getClient(){
        if (RETROFIT==null){

            RETROFIT = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return RETROFIT;
    }
}
