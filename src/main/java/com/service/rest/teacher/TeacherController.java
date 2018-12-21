package com.service.rest.teacher;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class TeacherController {

    private TeacherRepository repository;
    private TeacherResourceAssembler assembler;

    public TeacherController(TeacherRepository repository, TeacherResourceAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/teachers")
    Resources all() {
        List teachers = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources(teachers, linkTo(methodOn(TeacherController.class).all()).withSelfRel());
    }

    @GetMapping("/teachers/{id}")
    Resource one(@PathVariable int id) {
        Teacher teacher = repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
        return assembler.toResource(teacher);
    }

    @PostMapping("/teachers")
    ResponseEntity<?> newGroup(@RequestBody Teacher newTeacher) throws URISyntaxException {
        Resource resource = assembler.toResource(repository.save(newTeacher));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @PutMapping("/teachers/{id}")
    ResponseEntity<?> replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable int id) throws URISyntaxException {
        Teacher updatedTeacher = repository.findById(id)
                .map(teacher -> {
                    teacher.setName(newTeacher.getName());
                    teacher.setSurname(newTeacher.getSurname());
                    return repository.save(teacher);
                })
                .orElseGet(() -> {
                    newTeacher.setId(id);
                    return repository.save(newTeacher);});

        Resource resource = assembler.toResource(updatedTeacher);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);

    }
}
