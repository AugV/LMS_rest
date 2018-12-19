package com.service.rest;


import com.service.rest.entities.Teacher;
import com.service.rest.entities.TeacherRepository;
import com.service.rest.exceptions.TeacherNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class TeacherController {

    private final TeacherRepository repository;

    public TeacherController(TeacherRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/teachers")
    List<Teacher> all() {
        return repository.findAll();
    }

    @PostMapping("/teachers")
    Teacher newTeacher(@RequestBody Teacher newTeacher) {
        return repository.save(newTeacher);
    }

    @GetMapping("/teachers/{id}")
    Teacher one(@PathVariable int id) {
        return repository.findById(id).orElseThrow(() -> new TeacherNotFoundException(id));
    }

    @PutMapping("/teachers/{id}")
    Teacher replaceTeacher(@RequestBody Teacher newTeacher, @PathVariable int id) {
        return repository.findById(id)
                .map(teacher -> {
                    teacher.setName(newTeacher.getName());
                    teacher.setSurname(newTeacher.getSurname());
                    return repository.save(teacher);
                })
                .orElseGet(()->{
                    newTeacher.setId(id);
                    return repository.save(newTeacher);
                });
    }
}
