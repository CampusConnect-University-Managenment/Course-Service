package com.example.course.service.impl;

import com.example.course.dto.CourseDTO;
import com.example.course.entity.Course;
import com.example.course.exception.CourseNotFoundException;
import com.example.course.repository.CourseRepository;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course createCourse(CourseDTO dto) {
        Course course = new Course(null, dto.getDegree(), dto.getDepartment(), dto.getYear(),
                dto.getCourseCode(), dto.getCourseName(), dto.getSemester(), dto.getCredits(), dto.getColor());
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(String id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
    }

    @Override
    public Course updateCourse(String id, CourseDTO dto) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course not found with id: " + id));
        course.setDegree(dto.getDegree());
        course.setDepartment(dto.getDepartment());
        course.setYear(dto.getYear());
        course.setCourseCode(dto.getCourseCode());
        course.setCourseName(dto.getCourseName());
        course.setSemester(dto.getSemester());
        course.setCredits(dto.getCredits());
        course.setColor(dto.getColor());
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(String id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course not found with id: " + id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesForStudent(String department, String year, String semester) {
        return courseRepository.findByDepartmentAndYearAndSemester(department, year, semester);
    }
}
