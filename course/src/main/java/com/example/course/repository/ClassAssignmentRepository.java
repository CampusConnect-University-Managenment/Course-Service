package com.example.course.repository;

import com.example.course.entity.ClassAssignment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClassAssignmentRepository extends MongoRepository<ClassAssignment, String> {
}
