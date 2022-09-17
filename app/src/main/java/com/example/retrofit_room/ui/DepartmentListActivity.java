package com.example.retrofit_room.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.retrofit_room.R;
import com.example.retrofit_room.model.Department;

import java.util.ArrayList;
import java.util.List;

public class DepartmentListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DepartmentAdapater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);

        List<Department> list = new ArrayList<>();
        list.add(new Department(1,"TI"));
        list.add(new Department(2,"SAUDE"));
        list.add(new Department(3,"HUMANAS"));

        adapter = new DepartmentAdapater(list);

        rvList = findViewById(R.id.rvDepartmentList);

        rvList.setAdapter(adapter);

    }
}