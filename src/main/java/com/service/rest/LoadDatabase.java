package com.service.rest;


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
    CommandLineRunner initDatabase(TeacherRepository repository) {
        return args -> {

            log.info("Preloading " + repository.save(new Teacher("Tadas", "Jabli")));
            log.info("Preloading " + repository.save(new Teacher("Jonas", "KAponas")));
        };

    }
}