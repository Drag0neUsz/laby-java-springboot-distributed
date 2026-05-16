package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.model.dto.GradeDetailsDTO;
import com.example.SpringBootApp.model.dto.StudentDTO;
import com.example.SpringBootApp.service.GatewayService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final GatewayService gatewayService;

    public GatewayController(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @GetMapping("/students/{id}/gpa")
    public Double getAverage(@PathVariable Integer id) {
        return gatewayService.getStudentWeightedAverage(id);
    }

    @GetMapping("/students/{id}/grades")
    public List<GradeDetailsDTO> getGrades(@PathVariable Integer id) {
        return gatewayService.getStudentGrades(id);
    }

    @GetMapping("/courses/{id}/failed")
    public Long getFailedCount(@PathVariable Integer id) {
        return gatewayService.countFailedStudents(id);
    }

    @GetMapping("/students/top")
    public List<StudentDTO> getTopStudents() {
        return gatewayService.getTopStudents();
    }
}