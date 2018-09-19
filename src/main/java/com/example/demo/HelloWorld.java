package com.example.demo;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.dto.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloWorld {
    ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
    EmployeeDao dao = (EmployeeDao)ctx.getBean("employee");

    @CrossOrigin
    @RequestMapping("/getallemployees")
    public List<Employee> home(){
        List<Employee> emps = dao.getAllEmployees();
        return emps;
    }

    @CrossOrigin
    @RequestMapping("/getbyid")
    public Employee getEmployee(String name){
        return dao.getEmployeeByName(name);
    }

    @CrossOrigin
    @DeleteMapping("removeemployee")
    public void removeEmployeebyId(String name){
        dao.removeEmployeeByName(name);

    }


    @CrossOrigin
    @RequestMapping("/test")
    public String ho(){
        return "{\"content\": \"Herro Worrd\"}";
    }

    @CrossOrigin
    @PostMapping("/getemployeebyname")
    public Employee getEmployeeById(@RequestBody Employee emp){
        return dao.getEmployeeByName(emp.getName());
    }

    @CrossOrigin
    @PostMapping("/updateemployee")
    public void updateEmployee(@RequestBody Employee emp){
        dao.updateEmployee(emp);
    }


}
