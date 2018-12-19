package com.service.rest;


import com.service.rest.entities.Course;
import com.service.rest.entities.CourseRepository;
import com.service.rest.entities.Course;
import com.service.rest.entities.CourseRepository;
import com.service.rest.exceptions.CourseNotFoundException;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CourseController {

    private final CourseRepository repository;

    public CourseController(CourseRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/courses")
    public Resources<Resource<Course>> all() {

        List<Resource<Course>> courses = repository.findAll().stream()
                .map(course -> new Resource<>(course,
                        linkTo(methodOn(CourseController.class).one(course.getId())).withSelfRel(),
                        linkTo(methodOn(CourseController.class).all()).withRel("courses")))
                .collect(Collectors.toList());

        return new Resources<>(courses, linkTo(methodOn(CourseController.class).all()).withSelfRel());
    }

    @PostMapping("/courses")
    Course newCourse(@RequestBody Course newCourse) {
        return repository.save(newCourse);
    }

    @GetMapping("/courses/{id}")
    public Resource<Course> one(@PathVariable int id) {
        Course course = repository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));

        return new Resource<>(course,
                linkTo(methodOn(CourseController.class).one(id)).withSelfRel(),
        linkTo(methodOn(CourseController.class).all()).withRel("courses"));
    }

    @PutMapping("/courses/{id}")
    Course replaceCourse(@RequestBody Course newCourse, @PathVariable int id) {
        return repository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });
    }
}
