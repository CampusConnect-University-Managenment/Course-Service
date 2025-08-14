package com.example.course.feign;

import com.example.course.dto.StudentDTO;
import com.example.course.dto.FacultyDTO;
import com.example.course.dto.StudentSummaryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "profile-service", url = "http://localhost:8080")
public interface ProfileClient {

    // ✅ Fetch all faculty
    @GetMapping("/api/admin/faculty")
    List<FacultyDTO> getAllFaculty();

    // ✅ Fetch faculty by code
    @GetMapping("/api/admin/faculty/{facultyCode}")
    FacultyDTO getFacultyByCode(@PathVariable("facultyCode") String facultyCode);

    // ✅ Fetch student by ID
    @GetMapping("/api/admin/students/{studentId}")
    StudentDTO getStudentById(@PathVariable("studentId") String studentId);

    // ✅ Fetch student by Roll Number
    @GetMapping("/api/admin/students/roll/{rollNumber}")
    StudentDTO getStudentByRollNumber(@PathVariable("rollNumber") String rollNumber);

    // ✅ NEW: Bulk fetch students by IDs (for Enrollment → Assign Class)
    @PostMapping("/internal/students/bulk")
    List<StudentSummaryDTO> getStudentsByIds(@RequestBody List<String> studentIds);
}
