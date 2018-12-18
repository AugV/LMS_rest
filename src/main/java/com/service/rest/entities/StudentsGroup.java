package com.service.rest.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@javax.persistence.Entity
public class StudentsGroup extends Entity implements Serializable {
    @OneToMany(cascade= CascadeType.ALL, fetch = FetchType.LAZY)
    List<Student> groupStudents = new ArrayList();
    @OneToMany(cascade= CascadeType.MERGE, fetch = FetchType.EAGER)
    List<Course> groupCourses = new ArrayList();

    public StudentsGroup() {
    }

    public void addGroupCourse(Course groupCourse) {
        this.groupCourses.add(groupCourse);
    }

    public StudentsGroup(String name) {
        super(name);
    }

    public List<Student> getGroupStudents() {
        return groupStudents;
    }

    public void addGroupStudents(Student student) {
        student.setStudentsGroup(this);
        this.groupStudents.add(student);
    }

    public List<Course> getGroupCourses() {
        return groupCourses;
    }

    @Override
    public String toString() {
        return "entities.StudentsGroup{" +
                "groupID=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", groupStudents=" + groupStudents +
                '}';
    }
}
