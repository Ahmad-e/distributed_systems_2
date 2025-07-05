package com.example.employee_service.services;


import com.example.employee_service.entities.Employee;
import com.example.employee_service.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll(Long departmentId) {
        System.out.println("Implement Fetch Employees Data");
        if (departmentId == null)
            return employeeRepository.findAll();
        else return employeeRepository.findByDepartmentId(departmentId);
    }

    public List<Employee> getByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    public List<Employee> getByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    public String createEmployee(Employee employee) {
        employeeRepository.save(employee);
        return "Success";
    }

    public String deleteEmployee(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return "Employee is not exist";
        }
        employeeRepository.delete(optionalEmployee.get());
        return "success";
    }

    public String updateEmployee(Long id, Employee employee) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if (!optionalEmployee.isPresent()) {
            return "Employee is not exist";
        }
        Employee employee1 = optionalEmployee.get();
        employee1.setFirstName(employee.getFirstName());
        employee1.setLastName(employee.getLastName());
        employee1.setMobileNumber(employee.getMobileNumber());
        employeeRepository.save(employee1);
        return "success";
    }

}
