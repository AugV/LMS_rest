package com.service.rest.data_load;

import com.service.rest.course.Course;
import com.service.rest.course.CourseRepository;
import com.service.rest.teacher.Teacher;
import com.service.rest.teacher.TeacherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(TeacherRepository teacherRepository, CourseRepository courseRepository) {

        return args -> {

            //init Teachers
            Teacher teacher1 = teacherRepository.save(new Teacher("Mokytojas1", "Mokytojauskas1"));
            Teacher teacher2 = teacherRepository.save(new Teacher("Mokytojas2", "Mokytojauskas2"));

            //init Courses
            Course kursas1 = courseRepository.save(new Course("kursas1", "kursoInfo1"));
            Course kursas2 = courseRepository.save(new Course("kursas2", "kursoInfo2"));
            Course kursas3 = courseRepository.save(new Course("kursas3", "kursoInfo3"));

            //relation
            teacher1.setTeacherCourses(Arrays.asList(kursas1,kursas2));
            teacher2.setTeacherCourses(Arrays.asList(kursas3));
            kursas1.setCourseTeacher(teacher1);
            kursas2.setCourseTeacher(teacher1);
            kursas3.setCourseTeacher(teacher2);

            //saving
            teacherRepository.saveAll(Arrays.asList(teacher1,teacher2));
            courseRepository.saveAll(Arrays.asList(kursas1,kursas2,kursas3));

        };

    }

}