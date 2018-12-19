package com.service.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher extends Person{

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private List<Course> teacherCourses = new ArrayList();

    //private Student std = new Student("varda", "loxa");
    public Teacher() {
    }

    public Teacher(String loc_name, String loc_surname) {
        super(loc_name, loc_surname);
    }

    public Teacher(String loc_name, String loc_surname, Course teacherCourses) {
        super(loc_name, loc_surname);
        this.teacherCourses.add(teacherCourses);
    }

    public List<Course> getTeacherCourses() {
        return teacherCourses;
    }

    public void addTeacherCourses(Course course) {
            teacherCourses.add(course);
    }

    @Override
    public String toString() {
        return "ID: " + this.getId() +
                ", Name: " + this.getName() +
                ", Surname: " + this.getSurname() +
                " teacherCourses= " + teacherCourses +
                '}';
    }
}
