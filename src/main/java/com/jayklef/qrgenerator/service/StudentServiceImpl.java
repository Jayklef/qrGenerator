package com.jayklef.qrgenerator.service;

import com.jayklef.qrgenerator.exception.ResourceNotFoundException;
import com.jayklef.qrgenerator.model.Student;
import com.jayklef.qrgenerator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
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

        if (studentRepository.existsByEmail(student.getEmail())){
            throw new RuntimeException("Student with emailId of " + student.getEmail() + "already exists");
        }

        Student newStudent = new Student();
        newStudent.setFirstName(student.getFirstName());
        newStudent.setLastName(student.getLastName());
        newStudent.setEmail(student.getEmail());
        newStudent.setPhoneNo(student.getPhoneNo());

        return studentRepository.save(newStudent);
    }

    @Override
    public Student getStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);

        if (!student.isPresent()){
            throw new ResourceNotFoundException("Student with id of " + id + "not found");
        }
        return studentRepository.findById(id).get();
    }

    @Override
    public Student updateStudent(Long id, Student student) {

        Student studentInDb = studentRepository.findById(id).get();

        if (Objects.nonNull(student.getFirstName()) &&
        !"".equalsIgnoreCase(student.getFirstName())){
            studentInDb.setFirstName(student.getFirstName());
        }

        if (Objects.nonNull(student.getLastName()) &&
        !"".equalsIgnoreCase(student.getLastName())){
            studentInDb.setLastName(student.getLastName());
        }

        if (Objects.nonNull(student.getEmail()) &&
        !"".equalsIgnoreCase(studentInDb.getEmail())){
            studentInDb.setEmail(student.getEmail());
        }

        if (Objects.nonNull(student.getPhoneNo()) &&
        "".equalsIgnoreCase(student.getPhoneNo().toString())){
            studentInDb.setPhoneNo(student.getPhoneNo());
        }

        return studentRepository.save(studentInDb);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = getStudent(id);

        studentRepository.delete(student);
    }
}
