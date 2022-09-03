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

    @GET("/departments/{department_id}")
    Call<List<Department>> getDepartmentByID(@Path("department_id") Long id);

    @POST("/departments")
    Call <Department> createDepartment(@Body DepartmentDTO dto);

    @PUT("/departments/{department_id}")
    Call <Department> updateDepartment(@Path("department_id") Long id, @Body DepartmentDTO dto);

    @DELETE("/departments/{department_id}")
    Call <Department> deleteDepartment(@Path("department_id") Long id);
}
