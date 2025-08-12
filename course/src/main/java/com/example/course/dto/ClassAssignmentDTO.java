package com.example.course.dto;

import lombok.Data;
import java.util.List;

@Data
public class ClassAssignmentDTO {
    private String year;
    private String semester;
    private String department;
    private String courseId;
    private String facultyId;
    private List<String> studentIds;
}
