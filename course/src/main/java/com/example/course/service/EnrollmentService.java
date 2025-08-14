package com.example.course.service;

import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.dto.StudentSummaryDTO;

import java.util.List;

public interface EnrollmentService {
    List<EnrollmentDTO> enroll(EnrollmentRequest request); // idempotent per (student,course,semester)
    List<EnrollmentDTO> getByStudentAndSemester(String studentId, String semester);
    List<String> getStudentIdsByCourse(String courseCode, String department, String semester);
    List<StudentSummaryDTO> getStudentDetailsByCourse(String courseCode, String department, String semester);
    void resetAll(); // demo helper
}
