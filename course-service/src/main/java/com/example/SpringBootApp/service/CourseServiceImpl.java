package com.example.SpringBootApp.service;

import com.example.SpringBootApp.exception.*;
import com.example.SpringBootApp.model.Course;
import com.example.SpringBootApp.repository.CourseRepository;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public Course addCourse(Course course) throws InvalidNameException, CourseInvalidEctsException {
        if (course.getName() == null || course.getName().trim().isEmpty()) {
            throw new InvalidNameException();
        }

        if (course.getEcts() == null || course.getEcts() < 0 || course.getEcts() > 30) {
            throw new CourseInvalidEctsException();
        }
        return courseRepository.save(course);
    }

    @Override
    public Course updateCourse(Integer id, Course courseDetails) throws CourseNotFoundException, InvalidNameException, CourseInvalidEctsException {
        if (courseDetails.getName() == null || courseDetails.getName().trim().isEmpty()) {
            throw new InvalidNameException();
        }
        if (courseDetails.getEcts() == null || courseDetails.getEcts() < 0 || courseDetails.getEcts() > 30) {
            throw new CourseInvalidEctsException();
        }
        Course course = courseRepository.findById(id)
                .orElseThrow(CourseNotFoundException::new);
        course.setName(courseDetails.getName());
        course.setEcts(courseDetails.getEcts());
        return courseRepository.save(course);
    }

    @Override
    public boolean deleteCourse(Integer id) throws CourseNotFoundException, CourseHasGradesException {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException();
        }

        courseRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Course> getCoursesByEcts(Integer ects) throws CourseNotFoundException{

        List<Course> courses = courseRepository.findByEcts(ects);
        if (courses.isEmpty()) throw new CourseNotFoundException();
        return courses;
    }
}