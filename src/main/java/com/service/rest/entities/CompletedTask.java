package com.service.rest.entities;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Data
@javax.persistence.Entity
public class CompletedTask extends Entity implements Serializable {
    private static DateFormat dateFormat = new SimpleDateFormat("yyyy/mm/dd");
    private static Calendar calendar = Calendar.getInstance();

    @ManyToOne
    private Student owner;

    @Column
    private String answer;
    @Column
    private String grade;
    @Column
    private String submissionDate;
    @Column
    private String gradeDate;

    public CompletedTask() {
    }

    public CompletedTask(String answer, Student owner) {
        super();
        this.answer = answer;
        this.submissionDate = dateFormat.format(calendar.getTime());
        this.owner = owner;
    }

}
