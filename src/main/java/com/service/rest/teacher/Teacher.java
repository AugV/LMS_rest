package com.service.rest.teacher;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.rest.course.Course;
import com.service.rest.entities.Person;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Data
@Entity
public class Teacher extends Person {

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> teacherCourses;

    public Teacher() {
    }

    public Teacher(String loc_name, String loc_surname) {
        super(loc_name, loc_surname);
    }
}
