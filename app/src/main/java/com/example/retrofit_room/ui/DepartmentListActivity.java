package com.example.retrofit_room.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofit_room.R;
import com.example.retrofit_room.RetrofitConfig;
import com.example.retrofit_room.model.Department;
import com.example.retrofit_room.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DepartmentListActivity extends AppCompatActivity {

    private RecyclerView rvList;
    private DepartmentAdapater adapter;
    private DepartmentService departmentService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_list);
        init();

       getAllDepartments();

        rvList.setAdapter(adapter);

    }

    private void getAllDepartments(){
        departmentService.getAlldepartments().enqueue(
                new Callback<List<Department>>() {
                    @Override
                    public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                        List<Department> lista = response.body();
                        adapter.configureDepartmentAdapter(lista);

                    }

                    @Override
                    public void onFailure(Call<List<Department>> call, Throwable t) {
                        Toast.makeText(DepartmentListActivity.this, "Falha na lista de departments!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void init(){
        rvList = findViewById(R.id.rvDepartmentList);
        departmentService = RetrofitConfig.newInstance().departmentService();
        adapter = new DepartmentAdapater(new ArrayList<>());
    }

}