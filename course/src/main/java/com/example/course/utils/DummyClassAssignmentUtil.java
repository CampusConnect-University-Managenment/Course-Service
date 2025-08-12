package com.example.course.utils;

import com.example.course.entity.ClassAssignment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class DummyClassAssignmentUtil {

    public ClassAssignment getDummyAssignment() {
        return ClassAssignment.builder()
                .id(UUID.randomUUID().toString())
                .year("II")
                .semester("III")
                .department("IT")
                .courseId("COURSE123") // You can remove or keep for test purposes
                .facultyId("FAC001")
                .studentIds(List.of("STU001", "STU002", "STU003", "STU004"))
                .build();
    }

    public List<ClassAssignment> getDummyAssignments() {
        return List.of(getDummyAssignment());
    }

    // ❌ REMOVE this method since we won't use dummy courses anymore
    // public List<String> getCoursesFor(String department, String year, String semester) { ... }

    // ✅ Dummy faculty based on department
    public List<String> getFacultiesFor(String department) {
        return List.of(
                department + "_FAC001",
                department + "_FAC002",
                department + "_FAC003"
        );
    }

    // ✅ Dummy students based on department + year + semester
    public List<Map<String, String>> getStudentsFor(String department, String year, String semester) {
        return List.of(
                Map.of("roll", department + "_STU001", "name", "Student One"),
                Map.of("roll", department + "_STU002", "name", "Student Two"),
                Map.of("roll", department + "_STU003", "name", "Student Three")
        );
    }
}
