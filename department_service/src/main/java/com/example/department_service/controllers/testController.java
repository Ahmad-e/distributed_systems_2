package com.example.department_service.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/hello")
    public String sayHello() {
        return "مرحبا بك في Spring Boot!";
    }
}