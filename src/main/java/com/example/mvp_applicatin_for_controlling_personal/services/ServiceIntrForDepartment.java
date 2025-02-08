package com.example.mvp_applicatin_for_controlling_personal.services;

import com.example.mvp_applicatin_for_controlling_personal.entities.Department;

import java.util.List;

public interface ServiceIntrForDepartment
{
    public void ServiceForDepartments();

    public List<Department> getAllDepartments();
}
