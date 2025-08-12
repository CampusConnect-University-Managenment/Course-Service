package com.example.course.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student_enrollments")
public class StudentEnrollment {
    @Id
    private String studentId;
    private String name;
    private String rollNo;
    private String department;
    private String year;
    private String semester;
    private List<CourseSummary> courses;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CourseSummary {
        private String courseId;
        private String courseCode;
        private String courseName;
    }
}
