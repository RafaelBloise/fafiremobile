package com.example.retrofit_room.service;

import com.example.retrofit_room.model.Department;
import com.example.retrofit_room.model.dto.DepartmentDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DepartmentService {

    @GET("/departments")
    Call<List<Department>> getAlldepartments();

}
