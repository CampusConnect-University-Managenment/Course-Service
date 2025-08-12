package com.example.course.service.impl;

import com.example.course.dto.ClassAssignmentDTO;
import com.example.course.dto.FacultyDTO;
import com.example.course.entity.ClassAssignment;
import com.example.course.model.AssignmentWithDetails;
import com.example.course.repository.ClassAssignmentRepository;
import com.example.course.service.ClassAssignmentService;
import com.example.course.feign.ProfileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClassAssignmentServiceImpl implements ClassAssignmentService {

    private final ClassAssignmentRepository repository;
    private final ProfileClient profileClient;

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
    public List<AssignmentWithDetails> getAssignmentsWithDetails() {
        List<ClassAssignment> assignments = repository.findAll();

        return assignments.stream().map(a -> {
            String facultyName = getFacultyNameById(a.getFacultyId());
            return new AssignmentWithDetails(
                    a.getId(),
                    a.getCourseId(),
                    a.getFacultyId(),
                    facultyName,
                    a.getDepartment(),
                    a.getYear(),
                    a.getSemester(),
                    a.getStudentIds().size()
            );
        }).collect(Collectors.toList());
    }

    private String getFacultyNameById(String facultyId) {
        try {
            FacultyDTO faculty = profileClient.getFacultyByCode(facultyId);
            return faculty.getFirstName() + " " + faculty.getLastName();
        } catch (Exception e) {
            // Log exception if you have logger (not shown here)
            return "Unknown Faculty (" + facultyId + ")";
        }
    }

    @Override
    public List<ClassAssignment> getAllAssignments() {
        return repository.findAll();
    }
}
