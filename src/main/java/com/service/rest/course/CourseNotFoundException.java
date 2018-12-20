package com.service.rest.course;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(int id) {
        super("Could not find Course" + id);
    }
}
