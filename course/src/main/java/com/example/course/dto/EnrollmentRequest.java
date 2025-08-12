package com.example.course.dto;

import lombok.Data;

@Data
public class EnrollmentRequest {
    private String studentId;
    private String courseId;
}
