package com.example.course.controller;

import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student/enrollments")
@CrossOrigin(originPatterns = "*")

public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // Student enrolls to one or more courses for current semester
    @PostMapping
    public ResponseEntity<List<EnrollmentDTO>> enroll(@RequestBody EnrollmentRequest request) {
        return ResponseEntity.ok(enrollmentService.enroll(request));
    }

    // List a student's enrollments for a semester
    @GetMapping
    public ResponseEntity<List<EnrollmentDTO>> byStudentAndSem(
            @RequestParam String studentId,
            @RequestParam String semester
    ) {
        return ResponseEntity.ok(enrollmentService.getByStudentAndSemester(studentId, semester));
    }

    // DEMO helper to clear all enrollments
    @DeleteMapping("/_reset")
    public ResponseEntity<Void> resetAll() {
        enrollmentService.resetAll();
        return ResponseEntity.noContent().build();
    }
}
