package com.example.mvp_applicatin_for_controlling_personal.services;

import com.example.mvp_applicatin_for_controlling_personal.entities.Employee;

import java.util.List;

//
public interface ServiceIntrForEmployees
{
    public List<Employee> getAllEmployers(String department);

    public Employee findEmployee(String department, Integer id) ;

    public boolean deleteEmployee(Integer id);

    public Employee addEmployee(Employee employee) ;

    public Employee updateEmployee(String department, Long id, Employee updatedEmployee);
}
