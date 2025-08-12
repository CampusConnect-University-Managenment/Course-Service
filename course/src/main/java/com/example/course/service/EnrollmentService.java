package com.example.course.service;

import com.example.course.dto.CourseStudentListDTO;
import com.example.course.dto.CourseEnrollmentStatsDTO;
import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.entity.Enrollment;
import com.example.course.entity.Student;
import com.example.course.entity.StudentEnrollment;
import java.util.List;
import java.util.Map;

public interface EnrollmentService {

    Enrollment enrollStudent(EnrollmentRequest request);

    List<EnrollmentDTO> getEnrollmentsByCourse(String courseId);

    List<EnrollmentDTO> getEnrollmentsByCourseAndFilters(String courseId, String department, String year, String semester);

    List<EnrollmentDTO> getEnrollmentsByStudent(String studentId);

    StudentEnrollment getStudentEnrollmentByStudentId(String studentId);

    List<CourseEnrollmentStatsDTO> getCourseEnrollmentStats();

    List<Enrollment> getAllEnrollments();

    EnrollmentDTO mapToDTO(Enrollment enrollment);

    List<CourseStudentListDTO> getStudentsGroupedByCourse();

    List<Student> getStudentsByCourseCode(String courseCode);

    List<StudentEnrollment> getAllStudentEnrollments();

    Map<String, List<EnrollmentDTO>> getAllEnrollmentsGrouped();
}
