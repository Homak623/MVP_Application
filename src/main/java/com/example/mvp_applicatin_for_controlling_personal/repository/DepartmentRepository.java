package com.example.mvp_applicatin_for_controlling_personal.repository;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long>
{

    Optional<Object> findByName(String departmentName);
}
