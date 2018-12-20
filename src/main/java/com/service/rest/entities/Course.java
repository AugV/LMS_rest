package com.service.rest.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@javax.persistence.Entity
public class Course extends Entity implements Serializable {
    @Column
    private String information;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> courseTasks = new ArrayList();


    public Course() {
    }

    public Course(String name, String information) {
        super(name);
        this.information = information;
    }
}
