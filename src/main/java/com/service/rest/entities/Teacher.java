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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> teacherCourses = new ArrayList();

}
