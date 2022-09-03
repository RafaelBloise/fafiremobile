package com.example.retrofit_room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofit_room.model.Course;
import com.example.retrofit_room.model.Department;
import com.example.retrofit_room.model.dto.DepartmentDTO;
import com.example.retrofit_room.service.CourseService;
import com.example.retrofit_room.service.DepartmentService;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private DepartmentService departmentService;
    private CourseService courseService;
    private Button btnPost, btnPut, btnDelete;
    private EditText departmentUpdateName, departmentUpdateId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btnPost.setOnClickListener(view -> {
            createDepartment(departmentUpdateName.getText().toString());
        });

        btnPut.setOnClickListener(view ->{
            if(departmentUpdateId.getText()!=null){
                Long id = Long.valueOf(departmentUpdateId.getText().toString());
                updateDepartment(id,departmentUpdateName.getText().toString());
            }else{
                Toast.makeText(this, "Informe um ID para atualizar", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(view ->{
            if(departmentUpdateId.getText()!=null){
                Long id = Long.valueOf(departmentUpdateId.getText().toString());
                deleteDepartment(id);
            }else{
                Toast.makeText(this, "Informe um ID para excluir", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getAllDepartments(){
        departmentService.getAlldepartments().enqueue(
                new Callback<List<Department>>() {
                    @Override
                    public void onResponse(Call<List<Department>> call, Response<List<Department>> response) {
                        List<Department> lista = response.body();
                        for (Department item:lista) {
                            Log.i("DEPARTMENT >>>", item.getName());
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Department>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Falha na lista de departments!", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    private void getAllCourses(){
        courseService.getAllCourses().enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                List<Course> lista = response.body();
                for (Course item:lista) {
                    Log.i("COURSE >>>", item.getName());
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha na lista de courses", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createDepartment(String name){
        DepartmentDTO dto = new DepartmentDTO();

        dto.setName(name);
        departmentService.createDepartment(dto).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                if (response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Department criado com sucesso", Toast.LENGTH_SHORT).show();
                    departmentUpdateName.getText().clear();
                    if(departmentUpdateId.getText()!=null){
                        departmentUpdateId.getText().clear();
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Falha ao criar department", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha no request department", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateDepartment(Long id, String name){
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(name);

        departmentService.updateDepartment(id, dto).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                Toast.makeText(MainActivity.this, "Nome Atualizado", Toast.LENGTH_SHORT).show();
                departmentUpdateName.getText().clear();
                departmentUpdateId.getText().clear();

            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha ao realizar request de PUT Department", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteDepartment(Long id){
        departmentService.deleteDepartment(id).enqueue(new Callback<Department>() {
            @Override
            public void onResponse(Call<Department> call, Response<Department> response) {
                Toast.makeText(MainActivity.this, "Department excluído", Toast.LENGTH_SHORT).show();
                departmentUpdateId.getText().clear();
            }

            @Override
            public void onFailure(Call<Department> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Falha no request Delete department", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void init(){
        btnPost = findViewById(R.id.button_id);
        btnPut = findViewById(R.id.buttonPut_id);
        btnDelete = findViewById(R.id.buttonDeleteDep_id);
        departmentUpdateName = findViewById(R.id.editTextUpdate_id);
        departmentUpdateId = findViewById(R.id.editDepartmentUpdate_id);
        departmentService = RetrofitConfig.newInstance().departmentService();
        courseService = RetrofitConfig.newInstance().courseService();
    }

}