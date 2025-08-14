package com.example.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseAssignmentWithSubmissionsDTO {
    private String courseId;
    private String courseName;
    private String courseCode;
    private List<AssignmentInfo> assignments;

    @Data
    @AllArgsConstructor
    public static class AssignmentInfo {
        private String assignmentId;
        private String assignmentTitle;
        private List<StudentInfo> submittedStudents;

        @Data
        @AllArgsConstructor
        public static class StudentInfo {
            private String studentId;
            private String name;
            private String rollNo;
        }
    }
}
