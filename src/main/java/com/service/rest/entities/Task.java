package com.service.rest.entities;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@javax.persistence.Entity
public class Task extends Entity implements Serializable {

    @Column
    private String taskCondition;
    @Column
    private String deadline;
    @Column
    private String points;
    @Column
    private boolean allowSubmitCompletedTask = true;
    @OneToMany(cascade= CascadeType.ALL)
    private List<CompletedTask> taskCompletedTasks = new ArrayList();

    public Task() {
    }

    public Task(String name, String taskCondition, String deadline, String points) {
        super(name);
        this.taskCondition = taskCondition;
        this.deadline = deadline;
        this.points = points;
    }

}
