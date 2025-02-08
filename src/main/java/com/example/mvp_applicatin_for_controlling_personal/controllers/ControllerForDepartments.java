package com.example.mvp_applicatin_for_controlling_personal.controllers;

import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.services.ServiceForDepartments;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
public class ControllerForDepartments {

    private final ServiceForDepartments DepartmentService;

    @GetMapping("/getDepartments")
    public List<Department> getAllDepartments() {
        return DepartmentService.getAllDepartments();
    }

    @PostMapping("/addDepartment")
    public Department addDepartment(@RequestBody Department department) {
        return DepartmentService.addDepartment(department);
    }
}

