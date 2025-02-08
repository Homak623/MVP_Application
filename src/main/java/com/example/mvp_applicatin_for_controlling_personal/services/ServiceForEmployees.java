package com.example.mvp_applicatin_for_controlling_personal.services;

import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import com.example.mvp_applicatin_for_controlling_personal.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

///
@Service
@RequiredArgsConstructor
public class ServiceForEmployees {

    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployers(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public Employee findEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Employee not found with id: " + id));
    }

    @Transactional
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        employeeRepository.delete(employee);
    }


    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setSalary(updatedEmployee.getSalary());
        existingEmployee.setManager(updatedEmployee.getManager());

        return employeeRepository.save(existingEmployee);
    }


    public Employee assignManager(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));

        employee.setManager(true);
        return employeeRepository.save(employee);
    }
}

