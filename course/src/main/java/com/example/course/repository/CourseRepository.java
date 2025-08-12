package com.example.course.repository;

import com.example.course.entity.Course;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends MongoRepository<Course, String> {
    List<Course> findByDepartmentAndYearAndSemester(String department, String year, String semester);
    Optional<Course> findByCourseCode(String courseCode); // ADDED for lookup by courseCode
}
