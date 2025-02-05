package com.example.mvp_applicatin_for_controlling_personal.Services;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;

import java.util.List;
import java.util.stream.Collectors;

//
public interface ServiceIntrForEmployees
{
    public List<Employee> getAllEmployers(String department);

    public Employee findEmployee(String department, Integer id) ;

    public boolean deleteEmployee(String department, Integer id);

    public Employee addEmployee(Employee employee) ;

    public Employee updateEmployee(String department, Long id, Employee updatedEmployee);
}
