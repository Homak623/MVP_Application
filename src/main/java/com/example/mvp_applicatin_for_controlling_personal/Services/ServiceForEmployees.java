package com.example.mvp_applicatin_for_controlling_personal.Services;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import com.example.mvp_applicatin_for_controlling_personal.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
///
@Service
public class ServiceForEmployees {

    private final EmployeeRepository employeeRepository;

    public ServiceForEmployees(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    private final DepartmentRepository departmentRepository;

    public List<Employee> getAllEmployers(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public Employee findEmployee(String departmentName, Long id) {
        return employeeRepository.findByIdAndDepartmentName(id, departmentName).orElse(null);
    }

    @Transactional
    public boolean deleteEmployee(String departmentName, Long id) {
        Employee employee = findEmployee(departmentName, id);
        if (employee != null) {
            employeeRepository.delete(employee);
            return true;
        }
        return false;
    }


    public Employee addEmployee(Employee employee, String departmentName) {
        Department department = (Department) departmentRepository.findByName(departmentName)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(String departmentName, Long id, Employee updatedEmployee) {
        Employee existingEmployee = findEmployee(departmentName, id);
        if (existingEmployee != null) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setManager(updatedEmployee.getManager());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
}

