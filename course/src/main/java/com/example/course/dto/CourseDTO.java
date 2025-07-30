package com.example.course.dto;

import lombok.Data;

@Data
public class CourseDTO {
    private String degree;
    private String department;
    private String year;
    private String courseCode;
    private String courseName;
    private String semester;
    private int credits;
    private String color;
}