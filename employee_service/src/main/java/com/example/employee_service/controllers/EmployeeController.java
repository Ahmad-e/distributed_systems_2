package com.example.employee_service.controllers;


import com.example.employee_service.entities.Employee;
import com.example.employee_service.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(name = "departmentId", required = false) Long departmentId) {
        return new ResponseEntity<>(employeeService.getAll(departmentId), HttpStatus.OK);
    }

    @GetMapping("/firstName")
    public ResponseEntity<?> getByFirstName(@RequestParam(name = "firstName") String firstName){
        return new ResponseEntity<>(employeeService.getByFirstName(firstName), HttpStatus.OK);
    }

    @GetMapping("/lastName")
    public ResponseEntity<?> getByLastName(@RequestParam(name = "lastName") String lastName){
        return new ResponseEntity<>(employeeService.getByLastName(lastName), HttpStatus.OK);
    }

    @PostMapping("")
        public ResponseEntity<?> create(@ModelAttribute Employee employee){
        return new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@ModelAttribute Employee employee)  {
        return new ResponseEntity<>(employeeService.updateEmployee(id,employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return new ResponseEntity<>(employeeService.deleteEmployee(id), HttpStatus.OK);
    }
}
