package com.example.course.controller;

import com.example.course.dto.ClassAssignmentDTO;
import com.example.course.dto.FacultyDTO;
import com.example.course.entity.ClassAssignment;
import com.example.course.model.AssignmentWithDetails;
import com.example.course.response.CommonResponse;
import com.example.course.service.ClassAssignmentService;
import com.example.course.feign.ProfileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/class-assignments")
@RequiredArgsConstructor
public class ClassAssignmentController {

    private final ClassAssignmentService service;
    private final ProfileClient profileClient; // Feign client for faculty/profile
   // <-- new injection

    @PostMapping
    public ResponseEntity<CommonResponse<ClassAssignment>> assignClass(@RequestBody ClassAssignmentDTO dto) {
        ClassAssignment saved = service.createAssignment(dto);
        return ResponseEntity.ok(new CommonResponse<>(true, "Assignment created successfully", saved));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<ClassAssignment>>> getAllAssignments() {
        List<ClassAssignment> assignments = service.getAllAssignments();
        return ResponseEntity.ok(new CommonResponse<>(true, "Assignments fetched", assignments));
    }

    /**
     * Existing assignable endpoint (keeps returning faculties etc).
     * Students are fetched separately with the new endpoint below.
     */
    @GetMapping("/assignable")
    public ResponseEntity<CommonResponse<Map<String, Object>>> getAssignableData(
            @RequestParam String year,
            @RequestParam String department,
            @RequestParam String semester
    ) {
        Map<String, Object> response = new HashMap<>();

        // Call Profile Service to get faculties
        List<FacultyDTO> faculties = profileClient.getAllFaculty();
        response.put("faculties", faculties);

        // leave students empty here; frontend will call /students once a course is selected
        response.put("students", List.of());

        return ResponseEntity.ok(new CommonResponse<>(true, "Assignable data fetched", response));
    }

    /**
     * NEW endpoint:
     * Fetch enrolled students for a given course (filtered by department/year/semester).
     *
     * Frontend will call:
     * GET /admin/class-assignments/students?courseId={courseId}&department={dept}&year={year}&semester={semester}
     *
     * Response: CommonResponse with data = List<EnrollmentDTO>
     */

    @GetMapping("/with-names")
    public ResponseEntity<CommonResponse<List<AssignmentWithDetails>>> getAssignmentDetails() {
        List<AssignmentWithDetails> list = service.getAssignmentsWithDetails();
        return ResponseEntity.ok(new CommonResponse<>(true, "Detailed assignments fetched", list));
    }
}
