package com.example.department_service.controllers;

import com.example.department_service.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @GetMapping(value = "/ping", produces = MediaType.TEXT_PLAIN_VALUE)
    public String ping() {
        return "Department Service is alive!";
    }

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {

        return new ResponseEntity<>(departmentService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return new ResponseEntity<>(departmentService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<?> getOneWithEmployee(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(departmentService.getDetails(id), HttpStatus.OK);
    }
}
