package com.example.mvp_applicatin_for_controlling_personal.Controllers;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForDepartments;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class ControllerForDepartments
{
    private final ServiceForDepartments serviceForDepartments = new ServiceForDepartments();
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return serviceForDepartments.getAllDepartments();
    }
}
