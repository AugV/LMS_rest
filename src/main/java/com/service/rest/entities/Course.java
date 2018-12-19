package com.service.rest.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

    public Course(String name, String information, Teacher teacher, StudentsGroup studentsGroup) {
        super(name);
        this.information = information;
        teacher.addTeacherCourses(this);
        studentsGroup.addGroupCourse(this);
    }


    public String getCourseInformation() {
        return information;
    }

    public void addCourseTask(Task task) {
            courseTasks.add(task);
    }

    public void addCompletedTaskToCourseTaskById(int taskId, CompletedTask newCompletedTask) {
        for (Task task : courseTasks) {
            if (task.getId() == taskId) {
                task.addTaskCompletedTask(newCompletedTask);
            }
        }
    }

    public CompletedTask getCompletedTaskById(int completedTaskId) {
        CompletedTask completedTask = null;
        for (Task task : courseTasks) {
            completedTask = task.getCompletedTaskById(completedTaskId);
        }
        return completedTask;
    }

    public Task getCourseTaskByID(int id) {
        Task taskMatch = null;
        for (Task task : this.getCourseTasks()) {
            if (task.getId() == id) {
                taskMatch = task;
                break;
            }
        }
        return taskMatch;
    }

    public List<Task> getCourseTasks() {
        return courseTasks;
    }

    @Override
    public String toString() {
        return "entities.Course{" +
                "courseID=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", information='" + information + '\'' +
                ", courseTasks=" + courseTasks +
                '}';
    }
}
