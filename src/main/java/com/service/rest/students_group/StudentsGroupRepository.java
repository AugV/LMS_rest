package com.service.rest.students_group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentsGroupRepository extends JpaRepository<StudentsGroup, Integer> {
}
