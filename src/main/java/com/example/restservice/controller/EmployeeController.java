package com.example.restservice.controller;

import com.example.restservice.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{id}")
    public Employee modifyEmployee(@PathVariable Long id, @RequestBody Employee modifyEmployee) {
        for (Employee emp: employees) {
            if (emp.getId().equals(id)) {
                if (modifyEmployee.getName() != null) {
                    emp.setName(modifyEmployee.getName());
                }
                if (modifyEmployee.getDescription() != null) {
                    emp.setDescription(modifyEmployee.getDescription());
                }
                return emp;
            }
        }
        throw new RuntimeException("Employee not found!");
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        for (Employee employee: employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        throw new RuntimeException("Employee not found!");
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }
}
