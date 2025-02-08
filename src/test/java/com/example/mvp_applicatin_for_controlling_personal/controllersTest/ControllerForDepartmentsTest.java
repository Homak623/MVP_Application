package com.example.mvp_applicatin_for_controlling_personal.controllersTest;

import com.example.mvp_applicatin_for_controlling_personal.controllers.ControllerForDepartments;
import com.example.mvp_applicatin_for_controlling_personal.entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.services.ServiceForDepartments;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ExtendWith(SpringExtension.class)
@WebMvcTest(ControllerForDepartments.class)
public class ControllerForDepartmentsTest
{

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ServiceForDepartments serviceForDepartments;

    private Department department;

    @BeforeEach
    void setUp() {
        department = new Department(1L, "IT");
    }

    @Test
    public void testGetAllDepartments() throws Exception {
        when(serviceForDepartments.getAllDepartments()).thenReturn(List.of(department));

        mockMvc.perform(get("/departments/getDepartments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(department.getId()))
                .andExpect(jsonPath("$[0].name").value(department.getName()));

        verify(serviceForDepartments, times(1)).getAllDepartments();
    }


    @Test
    public void testAddDepartment() throws Exception {
        when(serviceForDepartments.addDepartment(any(Department.class))).thenReturn(department);

        mockMvc.perform(post("/departments/addDepartment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"IT\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(department.getId()))
                .andExpect(jsonPath("$.name").value(department.getName()));

        verify(serviceForDepartments, times(1)).addDepartment(any(Department.class));
    }

}

