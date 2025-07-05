package com.example.department_service.services;

import com.example.department_service.dto.DepartmentResponseDTO;
import com.example.department_service.dto.EmployeeResponseDTO;
import com.example.department_service.entities.Department;
import com.example.department_service.repositories.DepartmentRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAll(){

        return departmentRepository.findAll();
    }

    public Department getById(Long id){
         Optional<Department> department= departmentRepository.findById(id);
        return department.orElse(null);
    }

    @HystrixCommand(threadPoolKey= "depDetails",fallbackMethod = "fallbackDep",
            commandProperties = { @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "60000")})
    public DepartmentResponseDTO getDetails(Long id)  {
        Department department = getById(id);

        ResponseEntity<EmployeeResponseDTO[]> response = restTemplate.getForEntity("http://employee-service/employeeService/api/employees?departmentId=" + id, EmployeeResponseDTO[].class);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null)
            return null;

        return new DepartmentResponseDTO(id, department.getName(), Arrays.asList(response.getBody()));
    }
    @HystrixCommand(threadPoolKey= "depDetails")
    public DepartmentResponseDTO fallbackDep(Long id){
        Department department = getById(id);
        return new DepartmentResponseDTO(id, department.getName(), null);
    }


}
