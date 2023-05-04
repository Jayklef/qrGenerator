package com.jayklef.qrgenerator.repository;

import com.jayklef.qrgenerator.model.Student;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Boolean existsByEmail(String email);
}
