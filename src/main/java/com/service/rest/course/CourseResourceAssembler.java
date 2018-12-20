package com.service.rest.course;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Component
public class CourseResourceAssembler implements ResourceAssembler<Course, Resource> {

    @Override
    public Resource toResource(Course course) {
        return new Resource<>(course,
                linkTo(methodOn(CourseController.class).one(course.getId())).withSelfRel(),
                linkTo(methodOn(CourseController.class).all()).withRel("courses"));
    }
}
