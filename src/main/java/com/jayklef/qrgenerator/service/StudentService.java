package com.jayklef.qrgenerator.service;

import com.jayklef.qrgenerator.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(Long id);

    Student updateStudent(Long id, Student student);
}
