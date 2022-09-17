package com.example.retrofit_room.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofit_room.R;
import com.example.retrofit_room.model.Department;

import java.util.List;

public class DepartmentAdapater extends RecyclerView.Adapter<DepartmentItemHolder> {

    private List<Department> departments;

    public DepartmentAdapater(List<Department> departments) {
        this.departments = departments;
    }

    @NonNull
    @Override
    public DepartmentItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.department_item_layout,
                        parent,
                        false
                );

        return new DepartmentItemHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartmentItemHolder holder, int position) {
        Department department = departments.get(position);

        holder.setupItens(department.getName());
    }


    @Override
    public int getItemCount() {
        return departments.size();
    }
}
