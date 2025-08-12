package com.example.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentDTO {
    private String studentId;
    private String studentName;
    private String rollNo;
    private String department;
    private String year;
    private String semester;
    private String courseName;
}
