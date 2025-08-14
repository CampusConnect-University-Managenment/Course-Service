package com.example.course.controller;

import com.example.course.dto.StudentSummaryDTO;
import com.example.course.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/enrollments")
@CrossOrigin(originPatterns = "*")

public class EnrollmentAdminController {

    private final EnrollmentService enrollmentService;

    // For Assign Class: fetch student IDs enrolled for the selected course
    @GetMapping("/courses/{courseCode}/student-ids")
    public ResponseEntity<List<String>> studentIdsForCourse(
            @PathVariable String courseCode,
            @RequestParam String department,
            @RequestParam String semester
    ) {
        return ResponseEntity.ok(
                enrollmentService.getStudentIdsByCourse(courseCode, department, semester)
        );
    }

    // Same but with student details (name/roll) via Profile Service
    @GetMapping("/courses/{courseCode}/students")
    public ResponseEntity<List<StudentSummaryDTO>> studentsForCourse(
            @PathVariable String courseCode,
            @RequestParam String department,
            @RequestParam String semester
    ) {
        return ResponseEntity.ok(
                enrollmentService.getStudentDetailsByCourse(courseCode, department, semester)
        );
    }
}
