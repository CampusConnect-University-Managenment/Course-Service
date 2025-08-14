package com.example.course.repository;

import com.example.course.entity.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    Optional<Enrollment> findByStudentIdAndCourseCodeAndSemester(String studentId, String courseCode, String semester);
    List<Enrollment> findByStudentIdAndSemester(String studentId, String semester);
    List<Enrollment> findByCourseCodeAndDepartmentAndSemester(String courseCode, String department, String semester);
    List<Enrollment> findByStudentId(String studentId);
}
