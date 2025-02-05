package com.example.mvp_applicatin_for_controlling_personal.Services;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceForEmployees
{
    private List<Employee> employees = new ArrayList<Employee>();
    private static long ID = 0;

    {
        employees.add(new Employee(++ID,"Nikitos", 1000.1F,"Buca",false));
        employees.add(new Employee(++ID,"Zaharos", 1500.1F,"Buchalteria",false));
        employees.add(new Employee(++ID,"Egoros", 1500.1F,"Buchalteria",false));
    }

    public List<Employee> getAllEmployers(String department)
    {
        List<Employee> employeesOfDepartment = employees.stream()
                .filter(employee -> employee.getDepartment()
                        .equals(department)
        ).collect(Collectors.toList());
        return employeesOfDepartment;
    }

    public Employee findEmployee(String department, Integer id) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .filter(employee -> employee.getId().equals(Long.valueOf(id)))
                .findFirst()
                .orElse(null);
    }

    public boolean deleteEmployee(String department, Integer id)
    {
        return employees.removeIf(employee -> {
            if (employee.getDepartment().equals(department) && employee.getId().equals(Long.valueOf(id))){
                return true;
            }
            return false;
        });
    }

    public Employee addEmployee(Employee employee) {
        employee.setId(++ID);
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(String department, Long id, Employee updatedEmployee) {
        Employee existingEmployee = findEmployee(department, Math.toIntExact(id));
        if (existingEmployee != null) {
            existingEmployee.setName(updatedEmployee.getName());
            existingEmployee.setSalary(updatedEmployee.getSalary());
            existingEmployee.setManager(updatedEmployee.getManager());
            return existingEmployee;
        }
        return null;
    }

}
