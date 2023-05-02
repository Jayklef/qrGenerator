package com.jayklef.qrgenerator.service;

import com.jayklef.qrgenerator.model.Student;
import com.jayklef.qrgenerator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setFirstName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        newStudent.setPhoneNo(student.getPhoneNo());

        return studentRepository.save(newStudent);
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()){
            throw new RuntimeException("Student with id of " + id + "not found");
        }
        return studentRepository.findById(id).get();
    }
}
