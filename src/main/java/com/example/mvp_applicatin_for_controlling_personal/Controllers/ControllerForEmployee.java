package com.example.mvp_applicatin_for_controlling_personal.Controllers;

import com.example.mvp_applicatin_for_controlling_personal.Entities.Department;
import com.example.mvp_applicatin_for_controlling_personal.Entities.Employee;
import com.example.mvp_applicatin_for_controlling_personal.Services.ServiceForEmployees;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
///
@RestController
@RequestMapping("/employees")
public class ControllerForEmployee {

    private final ServiceForEmployees serviceForEmployees;

    public ControllerForEmployee(ServiceForEmployees serviceForEmployees) {
        this.serviceForEmployees = serviceForEmployees;
    }

    @GetMapping("/{department}")
    public List<Employee> getAllEmployees(@PathVariable String department) {
        return serviceForEmployees.getAllEmployers(department);
    }

    @PutMapping("/{department}/{id}")
    public ResponseEntity<Employee> assignManager(
            @PathVariable String department,
            @PathVariable Long id) {

        Employee employee = serviceForEmployees.findEmployee(department, id);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        employee.setManager(true);
        Employee updatedEmployee = serviceForEmployees.updateEmployee(department, id, employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{department}/{id}")
    public ResponseEntity<Void> deleteManager(@PathVariable String department, @PathVariable Long id) {
        boolean deleted = serviceForEmployees.deleteEmployee(department, id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping("/{department}")
    public ResponseEntity<Employee> addEmployee(
            @PathVariable String department,
            @RequestBody Employee employee) {

        Employee addedEmployee = serviceForEmployees.addEmployee(employee, department);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEmployee);
    }

    @PutMapping("/{department}/{id}/update")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable String department,
            @PathVariable Long id,
            @RequestBody Employee updatedEmployee) {

        Employee employee = serviceForEmployees.updateEmployee(department, id, updatedEmployee);
        if (employee == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(employee);
    }
}
