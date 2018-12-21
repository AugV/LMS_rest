package com.service.rest.course;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class CourseController {

    private final CourseRepository repository;
    private final CourseResourceAssembler assembler;

    public CourseController(CourseRepository repository, CourseResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/courses")
    public Resources all() {
        List courses = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources(courses, linkTo(methodOn(CourseController.class).all()).withSelfRel());
    }

    @GetMapping("/courses/{id}")
    public Resource one(@PathVariable int id) {
        Course course = repository.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
        return assembler.toResource(course);
    }

    @PostMapping("/courses")
    ResponseEntity<?> newCourse(@RequestBody Course newCourse) throws URISyntaxException {
        Resource resource = assembler.toResource(repository.save(newCourse));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @PutMapping("/courses/{id}")
    ResponseEntity<?> replaceCourse(@RequestBody Course newCourse, @PathVariable int id) throws URISyntaxException {
        Course updatedCourse = repository.findById(id)
                .map(course -> {
                    course.setName(newCourse.getName());
                    course.setInformation(newCourse.getInformation());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });
        Resource resource = assembler.toResource(repository.save(updatedCourse));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }
}
