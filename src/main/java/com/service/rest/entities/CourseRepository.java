package com.service.rest.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
//TODO projections for displaying nested objects

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
}
