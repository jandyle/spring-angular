package com.example.demo.dao.mapper;

import com.example.demo.dto.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee>{
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        Employee emp = new Employee();
        emp.setName(resultSet.getString(1));
        emp.setAddress(resultSet.getString(2));
        emp.setDepartment(resultSet.getString(3));
        emp.setSkills(resultSet.getString(4));
        return emp;
    }
}
