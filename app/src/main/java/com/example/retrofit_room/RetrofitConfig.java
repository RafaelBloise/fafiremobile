package com.example.retrofit_room;

import com.example.retrofit_room.service.DepartmentService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://professor-allocation.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RetrofitConfig newInstance() {return new RetrofitConfig();}

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public DepartmentService departmentService(){
        return retrofit.create(DepartmentService.class);
    }

}
