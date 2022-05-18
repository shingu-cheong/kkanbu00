package com.example.kkanbu.retrofit;

import com.example.kkanbu.pojo.Elder;

import java.util.HashMap;
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

public interface ElderEndPoint {

    @GET("/elder/list")
    Call<List<Elder>> getAllData();

    @GET("/elder/{id}")
    Call<Optional<Elder>> getDataById(@Path("id") int id);

    @POST("/elder/add")
    Call <Map> putNewDataOnDb(@Body Elder elder);

    @PUT("/elder/update")
    Call <Map> updateDataOnDb(@Body Elder elder) throws Exception;

    @DELETE("/elder/delete")
    Call <Map> deleteDataOnDb(@Body Elder elder) throws Exception;

    @GET("/userelder/{userid}")
    Call<List<Elder>> getUserelder(@Path("userid") int userid);
}
