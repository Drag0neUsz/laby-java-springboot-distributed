package com.example.SpringBootApp.controller;

import com.example.SpringBootApp.exception.GradeNotFoundException;
import com.example.SpringBootApp.model.Grade;
import com.example.SpringBootApp.service.GradeService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public List<Grade> getAll() {
        return gradeService.getAllGrades();
    }

    @GetMapping("/{id}")
    public Grade get(@PathVariable Integer id) throws GradeNotFoundException {
        return gradeService.getGrade(id);
    }

    @PostMapping
    public Grade add(@RequestBody Grade grade) {
        return gradeService.addGrade(grade);
    }

    @PutMapping("/{id}")
    public Grade update(@PathVariable Integer id, @RequestBody Grade grade) throws GradeNotFoundException {
        return gradeService.updateGrade(id, grade);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Integer id) {
        return gradeService.deleteGrade(id);
    }
}