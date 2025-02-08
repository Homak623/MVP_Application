package com.example.mvp_applicatin_for_controlling_personal.services;

import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceForDepartments {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {

        return departmentRepository.save(department);
    }

}

