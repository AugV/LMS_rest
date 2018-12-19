package com.service.rest.exceptions;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(int id) {
        super("Could not find Teacher" + id);
    }
}
