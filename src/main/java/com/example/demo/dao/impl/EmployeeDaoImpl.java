package com.example.demo.dao.impl;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.mapper.EmployeeRowMapper;
import com.example.demo.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("employee")
public class EmployeeDaoImpl implements EmployeeDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Employee employee) {
        String sql="insert into users.user values(?,?,?,?)";
        int res = jdbcTemplate.update(sql, employee.getName(), employee.getAddress(), employee.getDepartment(), employee.getSkills());
        return res;
    }

    @Override
    public Employee getEmployeeByName(String name) {
        String sql="select * from users.user where name=?";
        EmployeeRowMapper rowMapper = new EmployeeRowMapper();
        return jdbcTemplate.queryForObject(sql, rowMapper, name);

    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql="select * from users.user";
        EmployeeRowMapper rowMapper = new EmployeeRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void removeEmployeeByName(String name) {
        String sql ="delete from users.user where  name=?";
        System.out.print(jdbcTemplate.update(sql,name));
    }

    @Override
    public void updateEmployee(Employee emp) {
        String sql = "update users.user set name = '"+emp.getName()+"', address = '"+emp.getAddress()+"', department = '"+emp.getDepartment()+"', skill = '"+emp.getSkills()+"' where name='"+emp.getName()+"'";
//        System.out.println(emp.getName()+" : "+emp.getAddress()+" : "+emp.getDepartment()+" : "+emp.getSkills());
        System.out.println(sql);
        System.out.println(jdbcTemplate.update(sql));
    }


    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
