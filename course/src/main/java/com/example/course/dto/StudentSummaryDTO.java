package com.example.course.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentSummaryDTO {
    private String id;
    private String roll;
    private String name;
}
