package com.service.rest.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@MappedSuperclass
abstract public class Person extends Entity implements Serializable {

    @Column
    private String surname;

    public Person() {
    }

    public Person(String loc_name, String loc_surname) {
        super(loc_name);
        this.surname = loc_surname;
    }

}
