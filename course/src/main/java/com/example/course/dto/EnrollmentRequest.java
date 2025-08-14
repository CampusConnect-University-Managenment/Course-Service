package com.example.course.dto;

import lombok.Data;
import java.util.List;

@Data
public class EnrollmentRequest {
    private String studentId;
    private String year;             // I..IV
    private String semester;         // 1..8 as string
    private String department;
    private List<String> courseCodes; // enroll many at once
}
