package com.example.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AssignmentWithDetails {
    private String id;
    private String courseId;
    private String facultyId;
    private String facultyName;
    private String department;
    private String year;
    private String semester;
    private int studentCount;
}
