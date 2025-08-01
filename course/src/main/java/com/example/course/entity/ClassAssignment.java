package com.example.course.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "class_assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassAssignment {
    @Id
    private String id;
    private String year;
    private String semester;
    private String department;
    private String courseId;
    private String facultyId;
    private List<String> studentIds;
}
