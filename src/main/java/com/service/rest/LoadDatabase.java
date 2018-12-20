package com.service.rest;

import com.service.rest.entities.Course;
import com.service.rest.entities.CourseRepository;
import com.service.rest.entities.Teacher;
import com.service.rest.entities.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TeacherRepository teacherRepository, CourseRepository courseRepository) {

        return args -> {

            Teacher mokTestutis = teacherRepository.save(new Teacher("Testas", "Testutis"));
            Course kursas = courseRepository.save(new Course("Fizkultura", "pasprotuojam"));
            
//            mokTestutis.addTeacherCourses(kursas);
//            teacherRepository.save(mokTestutis);

        };

    }

}