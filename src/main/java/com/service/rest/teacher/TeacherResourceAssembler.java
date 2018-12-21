package com.service.rest.teacher;

import com.service.rest.course.Course;
import com.service.rest.course.CourseController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
class TeacherResourceAssembler implements ResourceAssembler<Teacher, Resource> {

    @Override
    public Resource toResource(Teacher teacher) {
        Resource resource = new Resource<>(teacher,
                linkTo(methodOn(TeacherController.class).one(teacher.getId())).withSelfRel(),
                linkTo(methodOn(TeacherController.class).all()).withRel("teachers"));

        for (Course course: teacher.getTeacherCourses()) {
            resource.add(linkTo(methodOn(CourseController.class).one(course.getId())).withSelfRel());
        }

        return resource;
    }
}
