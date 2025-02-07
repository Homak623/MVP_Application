package com.example.mvp_applicatin_for_controlling_personal.ControllersTest;
import com.example.mvp_applicatin_for_controlling_personal.Controllers.ControllerForEmployee;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForEmployees;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

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
        department = new Department(1L, "IT");
        employee = new Employee("John Doe", 3000.0, department, false);
        employee.setId(1L);
    }

    @Test
    public void testGetAllEmployees() throws Exception {
        when(serviceForEmployees.getAllEmployers(department.getName())).thenReturn(List.of(employee));

        mockMvc.perform(get("/employees/IT"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(employee.getId()))
                .andExpect(jsonPath("$[0].name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).getAllEmployers(department.getName());
    }

    @Test
    public void testAssignManager() throws Exception {
        when(serviceForEmployees.findEmployee(department.getName(), employee.getId())).thenReturn(employee);
        when(serviceForEmployees.updateEmployee(eq(department.getName()), eq(employee.getId()), any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(put("/employees/IT/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.manager").value(true));

        verify(serviceForEmployees, times(1)).findEmployee(department.getName(), employee.getId());
        verify(serviceForEmployees, times(1)).updateEmployee(eq(department.getName()), eq(employee.getId()), any(Employee.class));
    }

    @Test
    public void testDeleteManager() throws Exception {
        when(serviceForEmployees.deleteEmployee(department.getName(), employee.getId())).thenReturn(true);

        mockMvc.perform(delete("/employees/IT/1"))
                .andExpect(status().isNoContent());

        verify(serviceForEmployees, times(1)).deleteEmployee(department.getName(), employee.getId());
    }

    @Test
    public void testAddEmployee() throws Exception {
        when(serviceForEmployees.addEmployee(any(Employee.class), eq(department.getName())))
                .thenReturn(employee);

        mockMvc.perform(post("/employees/IT")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"salary\":3000.0, \"manager\":false}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).addEmployee(any(Employee.class), eq(department.getName()));
    }

    @Test
    public void testUpdateEmployee() throws Exception {
        when(serviceForEmployees.updateEmployee(eq(department.getName()), eq(employee.getId()), any(Employee.class)))
                .thenReturn(employee);

        mockMvc.perform(put("/employees/IT/1/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe Updated\", \"salary\":3500.0, \"manager\":true}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employee.getId()))
                .andExpect(jsonPath("$.name").value(employee.getName()));

        verify(serviceForEmployees, times(1)).updateEmployee(eq(department.getName()), eq(employee.getId()), any(Employee.class));
    }
}

