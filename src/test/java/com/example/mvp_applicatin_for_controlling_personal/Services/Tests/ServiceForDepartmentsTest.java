package com.example.mvp_applicatin_for_controlling_personal.Services.Tests;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForDepartments;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ServiceForDepartmentsTest
{
    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ServiceForDepartments serviceForDepartments;

    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department(1L, "GG");
    }

    @Test
    void testGetAllDepartments() {

        when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<Department> departments = serviceForDepartments.getAllDepartments();

        assertNotNull(departments);
        assertEquals(1, departments.size());
        assertEquals(department, departments.get(0));
    }

    @Test
    void testAddDepartment() {

        when(departmentRepository.save(department)).thenReturn(department);

        Department newDepartment = serviceForDepartments.addDepartment(department);

        assertNotNull(newDepartment);
        assertEquals(department.getId(), newDepartment.getId());
        assertEquals(department.getName(), newDepartment.getName());
    }
}
