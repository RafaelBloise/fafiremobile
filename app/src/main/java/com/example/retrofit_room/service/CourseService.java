package com.example.retrofit_room.service;

import com.example.retrofit_room.model.Course;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CourseService {

    @GET("/courses")
    Call<List<Course>> getAllCourses();
}
