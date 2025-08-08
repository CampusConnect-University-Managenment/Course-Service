package com.example.course.service;

import com.example.course.dto.ClassAssignmentDTO;
import com.example.course.entity.ClassAssignment;
import com.example.course.model.AssignmentWithDetails;

import java.util.List;

public interface ClassAssignmentService {
    ClassAssignment createAssignment(ClassAssignmentDTO dto);
    List<ClassAssignment> getAllAssignments();
    List<AssignmentWithDetails> getAssignmentsWithDetails();
}
