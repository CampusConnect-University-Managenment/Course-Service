package com.example.course.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentDTO {
    private String id;
    private String studentId;
    private String courseCode;
    private String department;
    private String year;
    private String semester;
}
