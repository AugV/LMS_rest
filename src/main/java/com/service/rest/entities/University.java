package com.service.rest.entities;

import com.service.rest.students_group.StudentsGroup;
import com.service.rest.teacher.Teacher;
import com.service.rest.course.Course;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@javax.persistence.Entity
public class University extends Entity implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;

    @OneToMany(cascade= CascadeType.ALL)
    private List<Teacher> teacherList = new ArrayList();
    @OneToMany(cascade= CascadeType.ALL)
    private List<StudentsGroup> studentsGroupList = new ArrayList();
    @OneToMany(cascade= CascadeType.ALL)
    private List<Course> courseList = new ArrayList();

    @Transient
    private StudentsGroup selectedStudentsGroup;

}
