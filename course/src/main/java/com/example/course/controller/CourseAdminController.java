package com.example.course.controller;

import com.example.course.dto.CourseDTO;
import com.example.course.entity.Course;
import com.example.course.response.CommonResponse;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/courses")
public class CourseAdminController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CommonResponse<Course>> createCourse(@RequestBody CourseDTO courseDTO) {
        Course created = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(new CommonResponse<>(true, "Course created successfully", created));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(new CommonResponse<>(true, "Courses retrieved", courses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<Course>> getCourseById(@PathVariable String id) {
        Course course = courseService.getCourseById(id);
        return ResponseEntity.ok(new CommonResponse<>(true, "Course found", course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<Course>> updateCourse(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        Course updated = courseService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(new CommonResponse<>(true, "Course updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<String>> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(new CommonResponse<>(true, "Course deleted successfully", id));
    }
}