package com.example.course.repository;

import com.example.course.entity.StudentEnrollment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentEnrollmentRepository extends MongoRepository<StudentEnrollment, String> {}
