package com.service.rest.students_group;

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
public class StudentsGroupController {

    StudentsGroupRepository repository;
    StudentsGroupResourceAssembler assembler;

    @GetMapping("/groups")
    Resources all() {
        List groups = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources(groups, linkTo(methodOn(StudentsGroupController.class).all()).withSelfRel());
    }

    @GetMapping("/groups/{id}")
    Resource one(@PathVariable int id) {
        StudentsGroup group = repository.findById(id).orElseThrow(() -> new StudentGroupNotFoundException(id));
        return assembler.toResource(group);
    }

    @PostMapping("/groups")
    ResponseEntity<?> newGroup(@RequestBody StudentsGroup newGroup) throws URISyntaxException {
        Resource resource = assembler.toResource(repository.save(newGroup));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

    @PutMapping("/groups/{id}")
    ResponseEntity<?> replaceGroup(@RequestBody StudentsGroup newGroup, @PathVariable int id) throws URISyntaxException {
        StudentsGroup updatedGroup = repository.findById(id)
                .map(studentsGroup -> {
                    studentsGroup.setName(newGroup.getName());
                    return repository.save(studentsGroup);
                })
                .orElseGet(() -> {
                    newGroup.setId(id);
                    return repository.save(newGroup);});

        Resource resource = assembler.toResource(updatedGroup);
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);

    }
}
