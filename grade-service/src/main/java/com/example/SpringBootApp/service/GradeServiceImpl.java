package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.GradeInvalidGradeException;
import com.example.SpringBootApp.exception.GradeNotFoundException;

import com.example.SpringBootApp.model.Grade;

import com.example.SpringBootApp.repository.GradeRepository;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    private final List<Double> allowedGrades = Arrays.asList(2.0, 3.0, 3.5, 4.0, 4.5, 5.0, 5.5);
    private void validateGradeValue(Double value) {
        if (value == null || !allowedGrades.contains(value)) {
            throw new GradeInvalidGradeException();
        }
    }

    private final GradeRepository gradeRepository;


    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }

    @Override
    public List<Grade> findByStudentId(Integer id) {
        return gradeRepository.findByStudentId(id);
    }

    @Override
    public Grade getGrade(Integer id) throws GradeNotFoundException {
        return gradeRepository.findById(id)
                .orElseThrow(GradeNotFoundException::new);
    }

    @Override
    public Grade addGrade(Grade grade) throws GradeInvalidGradeException {
        validateGradeValue(grade.getGrade());

        return gradeRepository.save(grade);
    }

    @Override
    public Grade updateGrade(Integer id, Grade gradeDetails) throws GradeNotFoundException, GradeInvalidGradeException {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(GradeNotFoundException::new);

        validateGradeValue(gradeDetails.getGrade());

        grade.setGrade(gradeDetails.getGrade());
        return gradeRepository.save(grade);
    }

    @Override
    public boolean deleteGrade(Integer id) throws GradeNotFoundException {
        if (!gradeRepository.existsById(id)) {
            throw new GradeNotFoundException();
        }
        gradeRepository.deleteById(id);
        return true;
    }
}