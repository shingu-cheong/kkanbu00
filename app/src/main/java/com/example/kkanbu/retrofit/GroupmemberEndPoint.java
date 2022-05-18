package com.example.kkanbu.retrofit;

import com.example.kkanbu.pojo.Elder;
import com.example.kkanbu.pojo.Groupmember;
import com.example.kkanbu.pojo.GroupmemberId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GroupmemberEndPoint{
    @GET("/list")
    public List<Groupmember> getAllData();

    @GET("/{id}")
    public Optional<Groupmember> getDataById(@Path("id") GroupmemberId id);

    @POST("/add")
    public Map putNewDataOnDb(@Body Groupmember groupmember);

    @PUT("/update")
    public Map updateDataOnDb(@Body Groupmember groupmember) throws Exception;

    @DELETE("/delete")
    public Map deleteDataOnDb(@Body Groupmember groupmember) throws Exception;
}
