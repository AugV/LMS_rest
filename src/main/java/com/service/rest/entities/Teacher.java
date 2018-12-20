package com.service.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Teacher extends Person{

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> teacherCourses;

    public Teacher() {
    }

    public Teacher(String loc_name, String loc_surname) {
        super(loc_name, loc_surname);
    }
}
