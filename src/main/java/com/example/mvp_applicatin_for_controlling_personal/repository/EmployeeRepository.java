package com.example.mvp_applicatin_for_controlling_personal.repository;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    List<Employee> findByDepartmentName(String departmentName);

    Optional<Employee> findByIdAndDepartmentName(Long id, String departmentName);
}
