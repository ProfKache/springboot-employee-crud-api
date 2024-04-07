package com.profkache.springboot.controller;

import com.profkache.springboot.entity.Employee;
import com.profkache.springboot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<Employee>(
                employeeService.saveEmployee(employee),
                HttpStatus.CREATED
        );
    }

    @GetMapping()
    public List<Employee> getEmployees() {
        return new ArrayList<>(employeeService.getEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId) {
        return new ResponseEntity<Employee>(
                employeeService.getEmployeeById(employeeId),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeById(@PathVariable("id") long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @RequestBody Employee employee,
            @PathVariable("id") long employeeId) {

        return new ResponseEntity<>(
                employeeService.updateEmployee(employee, employeeId),
                HttpStatus.OK
        );
    }

}
