package com.example.course.controller;

import com.example.course.dto.CourseDTO;
import com.example.course.response.CommonResponse;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/courses")
public class CourseAdminController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CommonResponse> createCourse(@RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(
                new CommonResponse(true, "Course created successfully", courseService.createCourse(courseDTO))
        );
    }

    @GetMapping
    public ResponseEntity<CommonResponse> getAllCourses() {
        return ResponseEntity.ok(
                new CommonResponse(true, "Courses fetched successfully", courseService.getAllCourses())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse> getCourseById(@PathVariable String id) {
        return ResponseEntity.ok(
                new CommonResponse(true, "Course fetched successfully", courseService.getCourseById(id))
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse> updateCourse(@PathVariable String id, @RequestBody CourseDTO courseDTO) {
        return ResponseEntity.ok(
                new CommonResponse(true, "Course updated successfully", courseService.updateCourse(id, courseDTO))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
        return ResponseEntity.ok(
                new CommonResponse(true, "Course deleted successfully", null)
        );
    }
}
