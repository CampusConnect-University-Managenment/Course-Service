package com.example.course.controller;

import com.example.course.dto.CourseStudentListDTO;
import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.dto.CourseEnrollmentStatsDTO;
import com.example.course.entity.Enrollment;
import com.example.course.entity.Student;
import com.example.course.entity.StudentEnrollment;
import com.example.course.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public ResponseEntity<Enrollment> enrollStudent(@RequestBody EnrollmentRequest request) {
        Enrollment enrollment = enrollmentService.enrollStudent(request);
        return ResponseEntity.ok(enrollment);
    }

    @GetMapping("/by-course/{courseId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourse(@PathVariable String courseId) {
        List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourse(courseId);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping("/by-course/filtered")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseAndFilters(
            @RequestParam String courseId,
            @RequestParam String department,
            @RequestParam String year,
            @RequestParam String semester) {
        List<EnrollmentDTO> filtered = enrollmentService.getEnrollmentsByCourseAndFilters(courseId, department, year, semester);
        return ResponseEntity.ok(filtered);
    }

    @GetMapping("/by-student/{studentId}")
    public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudent(studentId));
    }

    @GetMapping("/student-enrollment/{studentId}")
    public ResponseEntity<StudentEnrollment> getStudentEnrollment(@PathVariable String studentId) {
        return ResponseEntity.ok(enrollmentService.getStudentEnrollmentByStudentId(studentId));
    }

    @GetMapping("/enrollment-stats")
    public ResponseEntity<List<CourseEnrollmentStatsDTO>> getCourseEnrollmentStats() {
        return ResponseEntity.ok(enrollmentService.getCourseEnrollmentStats());
    }



    @GetMapping("/grouped-by-course")
    public ResponseEntity<List<CourseStudentListDTO>> getStudentsGroupedByCourse() {
        return ResponseEntity.ok(enrollmentService.getStudentsGroupedByCourse());
    }



    @GetMapping("/students-with-courses")
    public ResponseEntity<List<StudentEnrollment>> getAllStudentEnrollments() {
        return ResponseEntity.ok(enrollmentService.getAllStudentEnrollments());
    }
    @GetMapping("/students-by-course-code")
    public ResponseEntity<List<Student>> getStudentsByCourseCode(@RequestParam String courseCode) {
        List<Student> students = enrollmentService.getStudentsByCourseCode(courseCode);
        return ResponseEntity.ok(students);
    }
    @GetMapping("/all-enrollments-grouped")
    public ResponseEntity<Map<String, List<EnrollmentDTO>>> getAllEnrollmentsGrouped() {
        Map<String, List<EnrollmentDTO>> grouped = enrollmentService.getAllEnrollmentsGrouped();
        return ResponseEntity.ok(grouped);
    }

}
