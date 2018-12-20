package com.service.rest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@javax.persistence.Entity
public class Course extends Entity implements Serializable {
    @Column
    private String information;
    @OneToMany
    private List<Task> courseTasks;
    @JsonIgnore
    @ManyToOne
    private Teacher courseTeacher;

    public Course() {
    }

    public Course(String name, String information) {
        super(name);
        this.information = information;
    }
}
