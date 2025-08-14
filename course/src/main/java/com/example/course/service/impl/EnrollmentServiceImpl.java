package com.example.course.service.impl;

import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.dto.StudentSummaryDTO;
import com.example.course.entity.Enrollment;
import com.example.course.repository.EnrollmentRepository;
import com.example.course.service.EnrollmentService;
import com.example.course.feign.ProfileClient;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final ProfileClient profileClient;

    @Override
    public List<EnrollmentDTO> enroll(EnrollmentRequest request) {
        List<EnrollmentDTO> out = new ArrayList<>();
        for (String course : request.getCourseCodes()) {
            Enrollment e = Enrollment.builder()
                    .studentId(request.getStudentId())
                    .courseCode(course)
                    .department(request.getDepartment())
                    .year(request.getYear())
                    .semester(request.getSemester())
                    .createdAt(Instant.now())
                    .build();
            try {
                e = enrollmentRepository.save(e);
                out.add(toDto(e));
            } catch (DuplicateKeyException dup) {
                // already enrolled — fetch existing and return
                enrollmentRepository.findByStudentIdAndCourseCodeAndSemester(
                        request.getStudentId(), course, request.getSemester()
                ).ifPresent(existing -> out.add(toDto(existing)));
            }
        }
        return out;
    }

    @Override
    public List<EnrollmentDTO> getByStudentAndSemester(String studentId, String semester) {
        return enrollmentRepository.findByStudentIdAndSemester(studentId, semester)
                .stream().map(this::toDto).toList();
    }

    @Override
    public List<String> getStudentIdsByCourse(String courseCode, String department, String semester) {
        return enrollmentRepository
                .findByCourseCodeAndDepartmentAndSemester(courseCode, department, semester)
                .stream().map(Enrollment::getStudentId).toList();
    }

    @Override
    public List<StudentSummaryDTO> getStudentDetailsByCourse(String courseCode, String department, String semester) {
        List<String> ids = getStudentIdsByCourse(courseCode, department, semester);
        if (ids.isEmpty()) return List.of();
        // ProfileClient already in your project; ensure it exposes a bulk fetch method
        return profileClient.getStudentsByIds(ids); // returns List<StudentSummaryDTO>
    }

    @Override
    public void resetAll() {
        enrollmentRepository.deleteAll();
    }

    private EnrollmentDTO toDto(Enrollment e) {
        return EnrollmentDTO.builder()
                .id(e.getId())
                .studentId(e.getStudentId())
                .courseCode(e.getCourseCode())
                .department(e.getDepartment())
                .year(e.getYear())
                .semester(e.getSemester())
                .build();
    }
}
