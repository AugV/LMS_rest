package com.service.rest.students_group;

import com.service.rest.teacher.Teacher;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class StudentsGroupController {

    StudentsGroupRepository repository;
    StudentsGroupResourceAssembler assembler;

    @GetMapping("/groups")
    Resources all(){
        List groups = repository.findAll().stream()
                .map(assembler::toResource)
                .collect(Collectors.toList());
        return new Resources(groups, linkTo(methodOn(StudentsGroupController.class).all()).withSelfRel());
    }

    @GetMapping("/groups/{id}")
    Resource one(@PathVariable int id){
        StudentsGroup group = repository.findById(id).orElseThrow(()->new StudentGroupNotFoundException(id));
        return assembler.toResource(group);
    }



    @PutMapping("/groups/{id}")
    ResponseEntity<?> newGroup(@RequestBody StudentsGroup newGroup) throws URISyntaxException {
        Resource<StudentsGroup> resource= assembler.toResource(repository.save(newGroup));
        return ResponseEntity.created(new URI(resource.getId().expand().getHref())).body(resource);
    }

}
