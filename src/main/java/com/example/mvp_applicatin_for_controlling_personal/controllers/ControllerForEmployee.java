package com.example.mvp_applicatin_for_controlling_personal.controllers;

import com.example.mvp_applicatin_for_controlling_personal.entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.services.ServiceForEmployees;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class ControllerForEmployee
{

    private final ServiceForEmployees employeeService;

    @GetMapping
    public List<Employee> getAllEmployees(@RequestParam(required = false) String department) {
        return employeeService.getAllEmployers(department);
    }


    @PutMapping("/{id}")
    public Employee assignManager(@PathVariable Long id) {
        return employeeService.assignManager(id);
    }


    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }


    @PostMapping()
    public Employee addEmployee(

            @RequestBody Employee employee) {

        return employeeService.addEmployee(employee);
    }

    @PutMapping("/{id}/update")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        return employeeService.updateEmployee(id, updatedEmployee);
    }


}
