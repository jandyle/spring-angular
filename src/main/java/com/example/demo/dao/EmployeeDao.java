package com.example.demo.dao;

import com.example.demo.dto.Employee;

import java.util.List;

public interface EmployeeDao {
    int create(Employee employee);

    Employee getEmployeeByName(String name);

    List<Employee> getAllEmployees();

    void removeEmployeeByName(String name);

    void updateEmployee(Employee emp);

}
