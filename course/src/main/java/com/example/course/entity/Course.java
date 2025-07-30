package com.example.course.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "courses")
public class Course {
    @Id
    private String id;
    private String degree;
    private String department;
    private String year;
    private String courseCode;
    private String courseName;
    private String semester;
    private int credits;
    private String color;
}