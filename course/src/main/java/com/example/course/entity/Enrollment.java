package com.example.course.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "enrollments")
public class Enrollment {
    @Id
    private String id;
    @DBRef
    private Student student;
    @DBRef
    private Course course;
}
