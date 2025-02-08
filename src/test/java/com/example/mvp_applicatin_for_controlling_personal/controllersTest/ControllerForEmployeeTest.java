package com.example.mvp_applicatin_for_controlling_personal.controllersTest;
import com.example.mvp_applicatin_for_controlling_personal.controllers.ControllerForEmployee;
import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.services.ServiceForEmployees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class) // JUnit 5
@WebMvcTest(ControllerForEmployee.class) // Тестируем только контроллер
public class ControllerForEmployeeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceForEmployees serviceForEmployees;

    private Employee employee;
    private Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department(1L, "IT");
        employee = new Employee("John Doe", 3000.0, department, false);
        employee.setId(1L);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(serviceForEmployees.getAllEmployers(anyString())).thenReturn(List.of(employee));

        mockMvc.perform(get("/employees").param("department", "IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).getAllEmployers("IT");
    }

    @Test
    public void testAssignManager() throws Exception {
        when(serviceForEmployees.assignManager(employee.getId())).thenReturn(employee);

        mockMvc.perform(put("/employees/1"))
                .andExpect(status().isOk());

        verify(serviceForEmployees, times(1)).assignManager(employee.getId());
    }

    @Test
    public void testDeleteEmployee() throws Exception {
        doNothing().when(serviceForEmployees).deleteEmployee(employee.getId());

           mockMvc.perform(delete("/employees/1"))
                           .andExpect(status().isOk());

        verify(serviceForEmployees, times(1)).deleteEmployee(employee.getId());
    }

    @Test
    public void testAddEmployee() throws Exception {
        when(serviceForEmployees.addEmployee(any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"salary\":3000.0, \"manager\":false}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).addEmployee(any(Employee.class));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        when(serviceForEmployees.updateEmployee(eq(employee.getId()), any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(put("/employees/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe Updated\", \"salary\":3500.0, \"manager\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).updateEmployee(eq(employee.getId()), any(Employee.class));
    }
}

