package com.service.rest.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
abstract public class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    @Column
    private String name;

    public Entity() {
    }

    public Entity( String name){
        this.name = name;
    }

}
