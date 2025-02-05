package com.example.mvp_applicatin_for_controlling_personal.Services;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
///
@Service
public class ServiceForDepartments implements ServiceIntrForDepartment
{
    private List<Department> departments = new ArrayList<>();
    private static long ID = 0;

    public ServiceForDepartments() {
        departments.add(new Department(++ID, "Buchalteria"));
        departments.add(new Department(++ID, "OneMore"));
    }

    public List<Department> getAllDepartments() {
        return departments;
    }
}
