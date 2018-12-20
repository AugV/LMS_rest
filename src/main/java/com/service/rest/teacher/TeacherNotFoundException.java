package com.service.rest.teacher;

public class TeacherNotFoundException extends RuntimeException {

    public TeacherNotFoundException(int id) {
        super("Could not find teacher" + id);
    }
}
