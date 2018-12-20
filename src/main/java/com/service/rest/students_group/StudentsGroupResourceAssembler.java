package com.service.rest.students_group;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class StudentsGroupResourceAssembler implements ResourceAssembler<StudentsGroup, Resource> {
    @Override
    public Resource toResource(StudentsGroup studentsGroup) {
        return new Resource<>(studentsGroup,
                linkTo(methodOn(StudentsGroupController.class).one(studentsGroup.getId())).withSelfRel(),
                linkTo(methodOn(StudentsGroupController.class).all()).withRel("Group"));
    }
}
