package com.example.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseEnrollmentStatsDTO {
    private String courseName;
    private String courseCode;
    private long numberOfStudentsEnrolled;
}
