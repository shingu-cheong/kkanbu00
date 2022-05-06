package com.example.kkanbu.retrofit;

import com.example.kkanbu.pojo.Login;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LoginEndPoint {

    @GET("/login/list")
    public List<Login> getAllDate();

    @GET("/login/{id}")
    Call<Optional<Login>> getDataById(@Path("id") int id);

    @POST("/login/add")
    Call<Map> putNewDataOnDb(@Body Login login);

    @PUT("/login/update")
    Call<Map> updateDataOnDb(@Body Login login);

    @DELETE("/login/delete")
    Call<Map> deleteDataOnDb(@Body Login login);

    @POST("/login/check")
    Call<Map> checkUserNameAndPassword(@Body Login login);
}
