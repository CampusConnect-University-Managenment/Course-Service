package com.example.course.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class ErrorDetails {
    private Instant timestamp;
    private String message;
    private String details;
}
