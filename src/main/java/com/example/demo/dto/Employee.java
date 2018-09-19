package com.example.demo.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="user")
public class Employee implements Serializable
{
    private String name;
    private String address;
    private String department;
    private String skills;

    public Employee() {
        super();
    }

    public Employee(String name, String address, String department, String skills) {
        this.name = name;
        this.address = address;
        this.department = department;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }
}
