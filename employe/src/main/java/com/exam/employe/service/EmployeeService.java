package com.exam.employe.service;

import com.exam.employe.entity.Employee;
import com.exam.employe.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private MessageSource messageSource;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id, Locale locale) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException(messageSource.getMessage("employee.not.found", null, locale))
        );
    }

    public Employee addEmployee(Employee employee, Locale locale) {
        Optional<Employee> existingEmployee = repository.findByEmail(employee.getEmail());
        if (existingEmployee.isPresent()) {
            throw new RuntimeException(messageSource.getMessage("email.already.used", null, locale));
        }
        return repository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails, Locale locale) {
        Employee employee = getEmployeeById(id, locale);
        employee.setName(employeeDetails.getName());
        employee.setEmail(employeeDetails.getEmail());
        employee.setPosition(employeeDetails.getPosition());
        return repository.save(employee);
    }

    public void deleteEmployee(Long id, Locale locale) {
        Employee employee = getEmployeeById(id, locale);
        repository.delete(employee);
    }
}
