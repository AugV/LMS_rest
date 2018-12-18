package com.service.rest.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public abstract class Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int id;
    @Column
    private String name;

    public Entity() {
    }

    Entity( String name){
        this.name = name;
    }

    public int getId(){
        return id;
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
