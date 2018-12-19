package com.service.rest;


import com.service.rest.entities.Teacher;
import com.service.rest.entities.TeacherRepository;
import com.service.rest.exceptions.TeacherNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TeacherController {

    private final TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teachers")
    public Resources<Resource<Teacher>> all() {

        List<Resource<Teacher>> teachers = repository.findAll().stream()
                .map(teacher -> new Resource<>(teacher,
                        linkTo(methodOn(TeacherController.class).one(teacher.getId())).withSelfRel(),
                        linkTo(methodOn(TeacherController.class).all()).withRel("teachers")))
                .collect(Collectors.toList());

        return new Resources<>(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    @GetMapping("/teachers/{id}")
    public Resource<Teacher> one(@PathVariable int id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));

        return new Resource<>(teacher,
                linkTo(methodOn(TeacherController.class).one(id)).withSelfRel(),
        linkTo(methodOn(TeacherController.class).all()).withRel("teachers"));
    }

    @PutMapping("/teachers/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable int id) {
        return repository.findById(id)
                .map(teacher -> {
                    teacher.setName(newTeacher.getName());
                    teacher.setSurname(newTeacher.getSurname());
                    return repository.save(teacher);
                })
                .orElseGet(() -> {
                    newTeacher.setId(id);
                    return repository.save(newTeacher);
                });
    }
}
