package com.example.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseStudentListDTO {
    private String courseId;
    private String courseName;
    private String courseCode;
    private List<StudentInfo> students;

    @Data
    @AllArgsConstructor
    public static class StudentInfo {
        private String id;
        private String name;
        private String rollNo;
        private String department;
        private String year;
        private String semester;
    }
}
