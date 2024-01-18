package com.example.AngularDemo.service;

import com.example.AngularDemo.exception.UserNotFoundException;
import com.example.AngularDemo.model.Employee;
import com.example.AngularDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee){
        employee.setEmployeeCode(UUID.randomUUID().toString());
        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public Employee updateEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployeeById(Long id){
        return employeeRepository.findEmployeeById(id)
                .orElseThrow(()->new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteEmployee(Long id){
        boolean exists = employeeRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException(
                    "Employee with id " + id + " does not exists");
        }
        employeeRepository.deleteById(id);
    }
}
