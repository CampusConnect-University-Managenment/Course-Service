package com.example.course.service.impl;

import com.example.course.dto.CourseStudentListDTO;
import com.example.course.dto.CourseEnrollmentStatsDTO;
import com.example.course.dto.EnrollmentDTO;
import com.example.course.dto.EnrollmentRequest;
import com.example.course.entity.*;
import com.example.course.exception.EnrollmentAlreadyExistsException;
import com.example.course.exception.ResourceNotFoundException;
import com.example.course.repository.*;
import com.example.course.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;

    // Enroll student with department and year checks
    @Override
    public Enrollment enrollStudent(EnrollmentRequest request) {
        if (enrollmentRepository.existsByStudent_IdAndCourse_Id(request.getStudentId(), request.getCourseId())) {
            throw new EnrollmentAlreadyExistsException("Student is already enrolled in this course");
        }

        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student", request.getStudentId()));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", request.getCourseId()));

        if (!student.getDepartment().equals(course.getDepartment()) || !student.getYear().equals(course.getYear())) {
            throw new IllegalArgumentException("Student can only enroll in courses of their own department and year.");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

        updateStudentEnrollment(student, course);
        return savedEnrollment;
    }

    // Update or create student enrollment summary
    private void updateStudentEnrollment(Student student, Course course) {
        Optional<StudentEnrollment> existing = studentEnrollmentRepository.findById(student.getId());
        StudentEnrollment.CourseSummary courseSummary = new StudentEnrollment.CourseSummary(
                course.getId(), course.getCourseCode(), course.getCourseName());

        if (existing.isPresent()) {
            StudentEnrollment studentEnrollment = existing.get();
            List<StudentEnrollment.CourseSummary> courses = studentEnrollment.getCourses();
            if (courses == null) courses = new ArrayList<>();
            boolean alreadyExists = courses.stream().anyMatch(cs -> cs.getCourseId().equals(course.getId()));
            if (!alreadyExists) {
                courses.add(courseSummary);
                studentEnrollment.setCourses(courses);
                studentEnrollmentRepository.save(studentEnrollment);
            }
        } else {
            List<StudentEnrollment.CourseSummary> courses = new ArrayList<>();
            courses.add(courseSummary);
            StudentEnrollment studentEnrollment = new StudentEnrollment(
                    student.getId(),
                    student.getName(),
                    student.getRollNo(),
                    student.getDepartment(),
                    student.getYear(),
                    student.getSemester(),
                    courses
            );
            studentEnrollmentRepository.save(studentEnrollment);
        }
    }

    @Override
    public List<CourseStudentListDTO> getStudentsGroupedByCourse() {
        List<Course> courses = courseRepository.findAll();
        List<CourseStudentListDTO> result = new ArrayList<>();
        for (Course course : courses) {
            List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(course.getId());
            // SAFETY: Ignore enrollments with null course (shouldn't happen here, but extra robust)
            List<CourseStudentListDTO.StudentInfo> students = enrollments.stream()
                    .filter(e -> e.getStudent() != null)
                    .map(e -> {
                        Student s = e.getStudent();
                        return new CourseStudentListDTO.StudentInfo(
                                s.getId(), s.getName(), s.getRollNo(),
                                s.getDepartment(), s.getYear(), s.getSemester());
                    }).collect(Collectors.toList());

            result.add(new CourseStudentListDTO(course.getId(), course.getCourseName(), course.getCourseCode(), students));
        }
        return result;
    }

    @Override
    public List<StudentEnrollment> getAllStudentEnrollments() {
        return studentEnrollmentRepository.findAll();
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourse(String courseId) {
        // Ignore any enrollment with missing course or student
        return enrollmentRepository.findByCourse_Id(courseId).stream()
                .filter(e -> e.getCourse() != null && e.getStudent() != null)
                .map(this::mapToDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByCourseAndFilters(String courseId, String department, String year, String semester) {
        // Ignore any enrollment with missing course or student
        return enrollmentRepository
                .findByCourse_IdAndCourse_DepartmentAndCourse_YearAndCourse_Semester(courseId, department, year, semester)
                .stream()
                .filter(e -> e.getCourse() != null && e.getStudent() != null)
                .map(this::mapToDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public List<EnrollmentDTO> getEnrollmentsByStudent(String studentId) {
        return enrollmentRepository.findByStudent_Id(studentId).stream()
                .filter(e -> e.getCourse() != null && e.getStudent() != null)
                .map(this::mapToDTO)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public StudentEnrollment getStudentEnrollmentByStudentId(String studentId) {
        return studentEnrollmentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student enrollment not found for id: " + studentId));
    }

    @Override
    public List<CourseEnrollmentStatsDTO> getCourseEnrollmentStats() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();

        // SAFETY: Filter out any enrollment with null course
        Map<String, Long> enrollCountByCourseId = enrollments.stream()
                .filter(e -> e.getCourse() != null)
                .collect(Collectors.groupingBy(e -> e.getCourse().getId(), Collectors.counting()));

        List<CourseEnrollmentStatsDTO> stats = new ArrayList<>();
        for (Map.Entry<String, Long> entry : enrollCountByCourseId.entrySet()) {
            Course course = courseRepository.findById(entry.getKey()).orElse(null);
            if (course != null) {
                stats.add(new CourseEnrollmentStatsDTO(course.getCourseName(), course.getCourseCode(), entry.getValue()));
            }
        }
        return stats;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public EnrollmentDTO mapToDTO(Enrollment enrollment) {
        Student s = enrollment.getStudent();
        Course c = enrollment.getCourse();
        // Return null or handle if missing references
        if (s == null || c == null) return null;
        return new EnrollmentDTO(
                s.getId(),
                s.getName(),
                s.getRollNo(),
                s.getDepartment(),
                s.getYear(),
                s.getSemester(),
                c.getCourseName());
    }

    @Override
    public List<Student> getStudentsByCourseCode(String courseCode) {
        Course course = courseRepository.findByCourseCode(courseCode)
                .orElseThrow(() -> new ResourceNotFoundException("Course", courseCode));
        List<Enrollment> enrollments = enrollmentRepository.findByCourse_Id(course.getId());
        // SAFETY: Only return students with non-null reference
        return enrollments.stream()
                .map(Enrollment::getStudent)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    @Override
    public Map<String, List<EnrollmentDTO>> getAllEnrollmentsGrouped() {
        List<Enrollment> allEnrollments = enrollmentRepository.findAll();
        return allEnrollments.stream()
                .filter(e -> e.getCourse() != null)
                .collect(Collectors.groupingBy(
                        e -> e.getCourse().getCourseName() + " (" + e.getCourse().getCourseCode() + ")",
                        Collectors.mapping(this::mapToDTO, Collectors.toList())
                ));
    }
}
