package com.example.mvp_applicatin_for_controlling_personal.repository;

import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>
{
    Optional<Object> findByName(String departmentName);
}
