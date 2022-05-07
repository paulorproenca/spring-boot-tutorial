package com.example.springboottutorial.employee;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EmployeeRepository {

    private Map<Integer, Employee> DB = new HashMap<>();

    public List<Employee> getEmployeeList()
    {
        List<Employee> list = new ArrayList<>();
        if(list.isEmpty()) {
            list.addAll(DB.values());
        }
        return list;
    }

    public Employee getEmployeeById(int id) {
        return DB.get(id);
    }

    public void addEmployee(Employee employee) {
        employee.setEmployeeId(DB.keySet().size() + 1);
        DB.put(employee.getEmployeeId(), employee);
    }

    public void updateEmployee(Employee employee) {
        DB.put(employee.getEmployeeId(), employee);
    }

    public void deleteEmployee(int id) {
        DB.remove(id);
    }
}
