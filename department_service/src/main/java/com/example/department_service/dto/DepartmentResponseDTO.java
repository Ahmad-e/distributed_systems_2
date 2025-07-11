package com.example.department_service.dto;

import com.netflix.discovery.provider.Serializer;

import java.util.ArrayList;
import java.util.List;

@Serializer
public class DepartmentResponseDTO {

    private Long id;
    private String name;

    private List<EmployeeResponseDTO> employees = new ArrayList<>();

    public DepartmentResponseDTO() {
        employees = new ArrayList<>();
    }

    public DepartmentResponseDTO(Long id, String name, List<EmployeeResponseDTO> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeResponseDTO> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeResponseDTO> employees) {
        this.employees = employees;
    }
}
