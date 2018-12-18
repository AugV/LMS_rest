package com.service.rest.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher extends Person{

    @OneToMany(cascade= CascadeType.ALL)
    private List<Course> teacherCourses = new ArrayList();

    public Teacher() {
    }

    public Teacher(String loc_name, String loc_surname) {
        super(loc_name, loc_surname);
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
                " Teacher{" +
                " teacherCourses= " + teacherCourses +
                '}';
    }
}
