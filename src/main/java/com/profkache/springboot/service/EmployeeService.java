package com.profkache.springboot.service;

import com.profkache.springboot.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public Employee saveEmployee(Employee employee);
    public List<Employee> getEmployees();
    Employee getEmployeeById(long id);
    public void deleteEmployeeById(long id);
    Employee updateEmployee(Employee employee, long id);
}
