package com.service.rest.entities;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Student extends Person {
    @ManyToOne
    private StudentsGroup studentsGroup;

    public Student() {
    }

    public Student(String loc_name, String loc_surname) {
        super(loc_name, loc_surname);
    }

    public StudentsGroup getStudentsGroup() {
        return studentsGroup;
    }

    public void setStudentsGroup(StudentsGroup studentsGroup) {
        this.studentsGroup = studentsGroup;
    }

    @Override
    public String toString() {
        return " Student{" +
                "ID: " + getId() +
                ", Name: " + getName() +
                ", Surname: " + getSurname() +
                '}';
    }

}
