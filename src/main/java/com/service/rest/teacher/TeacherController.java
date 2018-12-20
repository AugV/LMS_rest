package com.service.rest.teacher;


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
    private final TeacherResourceAssembler assembler;

    public TeacherController(TeacherRepository repository, TeacherResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/teachers")
    public Resources all() {
        List teachers = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());

        return new Resources<>(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    @GetMapping("/teachers/{id}")
    public Resource one(@PathVariable int id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));

        return assembler.toResource(teacher);
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
