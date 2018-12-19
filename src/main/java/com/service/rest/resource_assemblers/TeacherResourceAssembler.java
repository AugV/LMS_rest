package com.service.rest.resource_assemblers;

import com.service.rest.TeacherController;
import com.service.rest.entities.Teacher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class TeacherResourceAssembler implements ResourceAssembler<Teacher, Resource> {

    @Override
    public Resource toResource(Teacher teacher) {
        return new Resource<>(teacher,
                linkTo(methodOn(TeacherController.class).one(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).all()).withRel("teachers"));
    }
}
