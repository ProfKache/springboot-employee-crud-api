package com.profkache.springboot.service.impl;

import com.profkache.springboot.entity.Employee;
import com.profkache.springboot.exception.ResourceNotFoundException;
import com.profkache.springboot.repository.EmployeeRepository;
import com.profkache.springboot.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(long id) {
//        Optional<Employee> employee = employeeRepository.findById(id);
//        if(employee.isPresent()) {
//            return employee.get();
//        } else  {
//            throw new ResourceNotFoundException("Employee", "Id", id);
//        }
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id)
        );
    }

    @Override
    public void deleteEmployeeById(long id) {
        employeeRepository.findById(id).orElseThrow(
                () ->  new ResourceNotFoundException("Employee", "Id", id)
        );
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            existingEmployee.get().setFirstName(employee.getFirstName());
            existingEmployee.get().setLastName(employee.getLastName());
            existingEmployee.get().setEmail(employee.getEmail());

            employeeRepository.save(existingEmployee.get());
            return  existingEmployee.get();
        } else {
            throw new ResourceNotFoundException("Employee", "Id", id);
        }
    }
}
