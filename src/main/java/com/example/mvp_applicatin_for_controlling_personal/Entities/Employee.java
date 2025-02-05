package com.example.mvp_applicatin_for_controlling_personal.Entities;


import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

public class Employee
{
    private Long id;
    private String name;
    private Double salary;
    private String department;

    public Employee(Long id, String name, Float salary, String department, Boolean manager) {
        this.id = id;
        this.name = name;
        this.salary = Double.valueOf(salary);
        this.department = department;
        this.manager = manager;
    }

    private Boolean manager;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getManager() {
        return manager;
    }

    public void setManager(Boolean manager) {
        this.manager = manager;
    }


}
