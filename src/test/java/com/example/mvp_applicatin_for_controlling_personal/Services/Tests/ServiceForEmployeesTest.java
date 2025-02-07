package com.example.mvp_applicatin_for_controlling_personal.Services.Tests;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForDepartments;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForEmployees;
import com.example.mvp_applicatin_for_controlling_personal.repository.DepartmentRepository;
import com.example.mvp_applicatin_for_controlling_personal.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ServiceForEmployeesTest {
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private ServiceForEmployees serviceForEmployees;

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ServiceForDepartments serviceForDepartments;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department((Long)1L, "It");
        employee = new Employee("Nikitos", (Double)2000.0, department, false);
    }

    @Test
    public void testGetAllEmployers() {
        when(employeeRepository.findByDepartmentName(department.getName())).thenReturn(List.of(employee));

        List<Employee> employees = serviceForEmployees.getAllEmployers(department.getName());

        assertNotNull(employees);
        assertEquals(1, employees.size());
        assertEquals(employee, employees.get(0));
    }

    @Test
    public void testFindEmployee() {
        when(employeeRepository.findByIdAndDepartmentName(employee.getId(), employee.getDepartment().getName()))
                .thenReturn(Optional.of(employee));

        Employee employee1 = serviceForEmployees.findEmployee(employee.getDepartment().getName(), employee.getId());

        assertNotNull(employee1);
        assertEquals(employee.getId(), employee1.getId());
        assertEquals(employee.getName(), employee1.getName());
        assertEquals(employee, employee1);
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeRepository.findByIdAndDepartmentName(employee.getId(), employee.getDepartment().getName()))
                .thenReturn(Optional.of(employee));

        doNothing().when(employeeRepository).delete(any(Employee.class));

        boolean result = serviceForEmployees.deleteEmployee(employee.getDepartment().getName(), employee.getId());

        verify(employeeRepository, times(1)).delete(employee);
        assertTrue(result);
    }

    @Test
    public void testAddEmployee()
    {
        when(departmentRepository.findByName(department.getName()))
                .thenReturn(Optional.of(department));

        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee employee1 = serviceForEmployees.addEmployee(employee, department.getName());

        assertNotNull(employee1);
        assertEquals(employee1, employee);
        verify(employeeRepository, times(1)).save(employee);
        verify(departmentRepository, times(1)).findByName(department.getName());
    }

    @Test
    public void testUpdateEmployee()
    {
        when(employeeRepository.findByIdAndDepartmentName(employee.getId(),
                employee.getDepartment().getName())).thenReturn(Optional.of(employee));

        when (employeeRepository.save(employee)).thenReturn(employee);

        Employee employee1 = serviceForEmployees.updateEmployee
                (department.getName(), employee.getId(), employee);

        assertNotNull(employee1);
        assertEquals(employee, employee1);

        verify(employeeRepository, times(1))
                .findByIdAndDepartmentName(employee.getId(), department.getName());

        verify(employeeRepository, times(1))
                .save(any(Employee.class));
    }
}
