package com.example.course.feign;

import com.example.course.dto.FacultyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "profile-service", url = "http://localhost:8080")
public interface ProfileClient {

    @GetMapping("/api/admin/faculty")
    List<FacultyDTO> getAllFaculty();

    @GetMapping("/api/admin/faculty/{facultyCode}")
    FacultyDTO getFacultyByCode(@PathVariable("facultyCode") String facultyCode);
}
