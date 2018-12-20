package com.service.rest.teacher;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class TeacherNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(TeacherNotFoundException.class)
    @ResponseStatus
    String teacherNotFoundHandler(TeacherNotFoundException e){
        return e.getMessage();
    }
}
