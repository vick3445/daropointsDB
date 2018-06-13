package com.example.vick.daropointsdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface Api_Interface {

    @GET("register.php")
    Call<ServerResponse>registerUser(@Query("firstname") String firstname, @Query("lastname") String lastname
            , @Query("username") String username, @Query("password") String password, @Query("id_number") String id_number);

    @GET("login.php")
    Call<ServerResponse>loginUser(@Query("username") String username, @Query("password") String password);
}
