package org.example.hexlet.dao;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.example.hexlet.model.Course;

public class CourseDao {

    private static final List<Course> COURSES = List.of(
        new Course(1L, "Java", "Learning Java"),
        new Course(2L, "Python", "Learning Python"),
        new Course(3L, "C++", "Learning C++")
    );

    public static List<Course> getCourse(Long id) {
        return COURSES.stream()
            .filter(course -> Objects.equals(course.getId(), id))
            .toList();
    }
}
