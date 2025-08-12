package com.example.course.controller;

import com.example.course.entity.Course;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseStudentController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/available")
    public ResponseEntity<List<Course>> getCoursesForStudent(
            @RequestParam String department,
            @RequestParam String year,
            @RequestParam String semester) {
        List<Course> courses = courseService.getCoursesForStudent(department, year, semester);
        return ResponseEntity.ok(courses);
    }
}
