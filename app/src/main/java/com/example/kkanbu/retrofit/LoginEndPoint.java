package com.example.kkanbu.retrofit;

import com.example.kkanbu.pojo.Login;
import com.example.kkanbu.pojo.User;

import java.util.List;
import java.util.Optional;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginEndPoint {


    @POST("/api/v1/user/check")
    Call<User> checkUser(@Body Login login);
}
