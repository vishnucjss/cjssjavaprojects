package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @GetMapping("/addEmployee")
        public Employee addEmployee(@RequestBody Employee employee)

        {
            return employeeRepository.save(employee);
        }
    @GetMapping("/getEmployeeById/{id}")
    public Employee addEmployee(@PathVariable Integer id)

    {
        return employeeRepository.findById(id).get();
    }
}
