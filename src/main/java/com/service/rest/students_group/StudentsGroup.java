package com.service.rest.students_group;

import com.service.rest.course.Course;
import com.service.rest.entities.Entity;
import com.service.rest.entities.Student;
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

    public StudentsGroup(String name) {
        super(name);
    }

}
