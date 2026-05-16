package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.dto.CourseDTO;
import com.example.SpringBootApp.model.dto.GradeDTO;
import com.example.SpringBootApp.model.dto.GradeDetailsDTO;
import com.example.SpringBootApp.model.dto.StudentDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class GatewayService {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String STUDENT_URL = "http://localhost:8081/students/";
    private final String COURSE_URL = "http://localhost:8082/courses/";
    private final String GRADE_URL = "http://localhost:8083/grades/";

    private void studentExists(Integer studentId) {
        try {
            restTemplate.getForObject(STUDENT_URL + studentId, StudentDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new StudentNotFoundException();
        }
    }


    private void courseExists(Integer courseId) {
        try {
            restTemplate.getForObject(COURSE_URL + courseId, CourseDTO.class);
        } catch (HttpClientErrorException.NotFound e) {
            throw new CourseNotFoundException();
        }
    }

    public Double getStudentWeightedAverage(Integer studentId) {
        studentExists(studentId);

        GradeDTO[] grades = restTemplate.getForObject(GRADE_URL + "student/" + studentId, GradeDTO[].class);
        if (grades == null || grades.length == 0) {
            return 0.0; // lub rzuć wyjątek, jeśli wolisz
        }

        double sumProduct = 0.0;
        int sumEcts = 0;

        for (GradeDTO grade : grades) {
            CourseDTO course = restTemplate.getForObject(COURSE_URL + grade.getCourseId(), CourseDTO.class);
            if (course != null) {
                sumProduct += grade.getGrade() * course.getEcts();
                sumEcts += course.getEcts();
            }
        }

        return sumEcts == 0 ? 0.0 : sumProduct / sumEcts;
    }



    public List<GradeDetailsDTO> getStudentGrades(Integer studentId) {
        studentExists(studentId);

        GradeDTO[] grades = restTemplate.getForObject(GRADE_URL + "student/" + studentId, GradeDTO[].class);
        if (grades == null || grades.length == 0) {
            throw new EmptyResultException("Student o ID " + studentId + " nie posiada żadnych ocen.");
        }

        return Arrays.stream(grades).map(g -> {
            CourseDTO course = restTemplate.getForObject(COURSE_URL + g.getCourseId(), CourseDTO.class);
            return new GradeDetailsDTO(g.getId(), g.getGrade(), course);
        }).toList();
    }

    public Long countFailedStudents(Integer courseId) {
        courseExists(courseId);

        return restTemplate.getForObject(GRADE_URL + "course/" + courseId + "/failed/count", Long.class);
    }
}