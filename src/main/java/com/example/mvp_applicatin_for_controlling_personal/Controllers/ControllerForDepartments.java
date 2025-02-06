package com.example.mvp_applicatin_for_controlling_personal.Controllers;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForDepartments;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//
@RestController
@RequestMapping("/departments")
public class ControllerForDepartments {

    private final ServiceForDepartments serviceForDepartments;

    public ControllerForDepartments(ServiceForDepartments serviceForDepartments) {
        this.serviceForDepartments = serviceForDepartments;
    }

    @GetMapping("/")
    public List<Department> getAllDepartments() {
        return serviceForDepartments.getAllDepartments();
    }

    @PostMapping("/")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
        Department newDepartment = serviceForDepartments.addDepartment(department);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
    }
}

