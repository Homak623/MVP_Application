package com.example.mvp_applicatin_for_controlling_personal.Services;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceForDepartments {

    private final DepartmentRepository departmentRepository;

    public ServiceForDepartments(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department addDepartment(Department department) {
        // Устанавливаем ссылку на департамент для всех сотрудников
        if (department.getEmployees() != null) {
            for (Employee employee : department.getEmployees()) {
                employee.setDepartment(department);
            }
        }
        return departmentRepository.save(department);
    }

}

