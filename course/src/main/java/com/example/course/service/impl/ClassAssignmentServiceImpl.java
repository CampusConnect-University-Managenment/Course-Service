package com.example.course.service.impl;

import com.example.course.dto.ClassAssignmentDTO;
import com.example.course.entity.ClassAssignment;
import com.example.course.repository.ClassAssignmentRepository;
import com.example.course.service.ClassAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassAssignmentServiceImpl implements ClassAssignmentService {

    private final ClassAssignmentRepository repository;

    @Override
    public ClassAssignment createAssignment(ClassAssignmentDTO dto) {
        ClassAssignment assignment = ClassAssignment.builder()
                .year(dto.getYear())
                .semester(dto.getSemester())
                .department(dto.getDepartment())
                .courseId(dto.getCourseId())
                .facultyId(dto.getFacultyId())
                .studentIds(dto.getStudentIds())
                .build();
        return repository.save(assignment);
    }

    @Override
    public List<ClassAssignment> getAllAssignments() {
        return repository.findAll();
    }
}
