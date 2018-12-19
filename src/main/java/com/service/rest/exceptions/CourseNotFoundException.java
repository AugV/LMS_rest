package com.service.rest.exceptions;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(int id) {
        super("Could not find Course" + id);
    }
}
