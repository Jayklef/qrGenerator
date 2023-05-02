package com.jayklef.qrgenerator.service;

import com.jayklef.qrgenerator.model.Student;
import com.jayklef.qrgenerator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student){
        Student newStudent = studentRepository.save(student);
        return newStudent;
    }
}
