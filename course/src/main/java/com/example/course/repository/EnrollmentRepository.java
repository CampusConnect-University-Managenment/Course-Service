package com.example.course.repository;

import com.example.course.entity.Enrollment;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EnrollmentRepository extends MongoRepository<Enrollment, String> {
    List<Enrollment> findByCourse_Id(String courseId);
    List<Enrollment> findByStudent_Id(String studentId);
    boolean existsByStudent_IdAndCourse_Id(String studentId, String courseId);
    List<Enrollment> findByCourse_IdAndCourse_DepartmentAndCourse_YearAndCourse_Semester(
            String courseId, String department, String year, String semester);
}
