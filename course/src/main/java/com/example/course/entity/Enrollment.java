package com.example.course.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "enrollments")
@CompoundIndex(
        name = "uniq_student_course_sem",
        def = "{'studentId': 1, 'courseCode': 1, 'semester': 1}",
        unique = true
)
public class Enrollment {
    @Id
    private String id;

    private String studentId;     // from Profile Service
    private String courseCode;    // e.g., CS101
    private String department;    // IT/CSE/ECE/...
    private String year;          // I..IV (roman)
    private String semester;      // 1..8 (string)

    private Instant createdAt;
}
