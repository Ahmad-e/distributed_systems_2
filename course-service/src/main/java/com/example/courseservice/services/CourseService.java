package com.example.courseservice.services;

import com.example.courseservice.models.Course;
import com.example.courseservice.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }
}
