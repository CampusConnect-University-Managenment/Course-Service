package com.example.course.service;

import com.example.course.dto.CourseDTO;
import com.example.course.entity.Course;

import java.util.List;

public interface CourseService {
    Course createCourse(CourseDTO dto);
    List<Course> getAllCourses();
    Course getCourseById(String id);
    Course updateCourse(String id, CourseDTO dto);
    void deleteCourse(String id);
}