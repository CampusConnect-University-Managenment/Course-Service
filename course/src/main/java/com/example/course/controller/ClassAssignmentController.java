package com.example.course.controller;

import com.example.course.dto.ClassAssignmentDTO;
import com.example.course.entity.ClassAssignment;
import com.example.course.response.CommonResponse;
import com.example.course.service.ClassAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/class-assignments")
@RequiredArgsConstructor
public class ClassAssignmentController {

    private final ClassAssignmentService service;

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
}
