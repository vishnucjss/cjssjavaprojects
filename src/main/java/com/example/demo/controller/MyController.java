package com.example.demo.controller;

import com.example.demo.model.Address;
import com.example.demo.model.Employee;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MyController {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private AddressRepository addressRepository;
    @GetMapping("/addEmployee")
        public Employee addEmployee(@RequestBody Employee employee)

        {
//             Address address1=employee.getAddress();
//             address1.setEmployee(employee);
//             employee.setAddress(address1);
             Employee employee1=employeeRepository.save(employee);

return employeeRepository.save(employee1);
        }
    @GetMapping("/getEmployeeById/{id}")
    public Employee addEmployee(@PathVariable Integer id)

    {
        return employeeRepository.findById(id).get();
    }
    @GetMapping("/get Address/{id}")
    public Address getAddress(@PathVariable Integer id)
    {
        return addressRepository.findById(id).get();
    }
    @DeleteMapping("/deleteEmployee/{id}")
    public void deleteEmployee(@PathVariable Integer id)
    {
        employeeRepository.deleteById(id);
    }
 @DeleteMapping("/deleteAddress/{id}")
    public void deleteAddress(@PathVariable Integer id)
 {
     addressRepository.deleteById(id);
 }
 @PutMapping("/updateAddress/{id}")
    public Employee UpdateAddress(@PathVariable Integer id)
 {
     Employee employee =employeeRepository.findById(id).get();
     employee.getAddress().setStateName("AndhraPradesh");
     return employee;

 }
// @PutMapping("/updateEmployee/{id}")
//    public Employee UpdateEmployee(@PathVariable Integer id )
// {
//     Address address=addressRepository.findById(id).get();
////      Employee employee=address.getEmployee();
////               employee.setName("kiran");
////               address.setEmployee(employee);
//               return addressRepository.save(address);
//
//
//
// }





}
