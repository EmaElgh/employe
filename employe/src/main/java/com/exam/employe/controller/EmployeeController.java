package com.exam.employe.controller;


import com.exam.employe.entity.Employee;
import com.exam.employe.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable Long id) {
        Locale locale = LocaleContextHolder.getLocale();
        return service.getEmployeeById(id, locale);
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        Locale locale = LocaleContextHolder.getLocale();
        return service.addEmployee(employee, locale);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Locale locale = LocaleContextHolder.getLocale();
        return service.updateEmployee(id, employeeDetails, locale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        Locale locale = LocaleContextHolder.getLocale();
        service.deleteEmployee(id, locale);
        return ResponseEntity.ok().body("Employee deleted successfully");
    }
}
