package com.example.course.controller;

import com.example.course.dto.CourseDTO;
import com.example.course.entity.Course;
import com.example.course.response.CommonResponse;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/class-assignments/dummy-data")
    public ResponseEntity<CommonResponse<?>> getDummyAssignmentData(
            @RequestParam String year,
            @RequestParam int semester,
            @RequestParam String department) {

        String[] roman = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII"};
        String semesterRoman = roman[semester - 1];

        // Dummy students
        List<Map<String, String>> students = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Map<String, String> student = new HashMap<>();
            student.put("roll", department + "-" + year + "-" + semesterRoman + "-" + String.format("%03d", i));
            student.put("name", "Student" + i);
            student.put("department", department);
            student.put("year", year);
            student.put("semester", semesterRoman);
            students.add(student);
        }

        // Dummy courses
        List<String> courses = List.of("Java Programming", "DBMS", "Machine Learning");

        // Dummy faculties
        List<String> faculties = List.of("Dr. Smith", "Prof. Johnson");

        Map<String, Object> response = new HashMap<>();
        response.put("students", students);
        response.put("courses", courses);
        response.put("faculties", faculties);

        return ResponseEntity.ok(new CommonResponse<>(true, "Dummy data retrieved successfully", response));
    }

}