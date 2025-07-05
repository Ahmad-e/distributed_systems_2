package com.example.employee_service.repositories;

import com.example.employee_service.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    @Query(value = "SELECT * FROM employee WHERE first_name like CONCAT('%',:firstName,'%')", nativeQuery = true)
    List<Employee> findByFirstName(String firstName);

    //If you don't want a custom query, you can use some basic queries that started with "findBy" and then the column name.
    List<Employee> findByLastName(String lastName);

    List<Employee> findByDepartmentId(Long departmentId);

}
