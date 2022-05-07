package com.example.springboottutorial.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService
{
    @Autowired
    EmployeeRepository dao;

    public List<Employee> getEmployeeList() {
        return dao.getEmployeeList();
    }

    public Employee getEmployeeById(int id) {
        return dao.getEmployeeById(id);
    }

    public Boolean addEmployee(Employee employee) {
        dao.addEmployee(employee);
        return true;
    }
}