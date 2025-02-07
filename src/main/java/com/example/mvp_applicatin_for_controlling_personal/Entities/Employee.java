package com.example.mvp_applicatin_for_controlling_personal.Entities;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

///

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "salary")
    private Double salary;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(name = "is_manager")
    private Boolean manager;

    public Employee(String name, Double salary, Department department, Boolean manager) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.manager = manager;
    }

    public Employee(Boolean manager, Double salary, String name) {
        this.manager = manager;
        this.salary = salary;
        this.name = name;
    }

    public Employee() {

    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getSalary() { return salary; }
    public void setSalary(Double salary) { this.salary = salary; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    public Boolean getManager() { return manager; }
    public void setManager(Boolean manager) { this.manager = manager; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(department, employee.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department);
    }
}
