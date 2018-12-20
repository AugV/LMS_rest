package com.service.rest.students_group;

public class StudentGroupNotFoundException extends RuntimeException {

    public StudentGroupNotFoundException(int id) {
        super("Group not found " + id);
    }
}
