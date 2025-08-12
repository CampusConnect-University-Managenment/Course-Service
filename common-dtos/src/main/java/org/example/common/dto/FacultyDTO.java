package org.example.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyDTO {

    private String id;
    private String facultyCode;
    private String firstName;
    private String lastName;
    private String email;

    private String departmentId;
    private String department;
    private String role;

    private String gender;
    private String address;
    private LocalDate dob;
    private int age;

    private String bloodGroup;
    private int experience;
    private LocalDate joiningDate;

    private String degree;
    private String photoUrl;
    private String contact;

    private List<String> courseIds;
}
