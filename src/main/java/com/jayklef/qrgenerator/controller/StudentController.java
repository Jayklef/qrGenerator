package com.jayklef.qrgenerator.controller;

import com.jayklef.qrgenerator.model.Student;
import com.jayklef.qrgenerator.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent(){
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Student> saveStudent(@RequestBody Student student){
        Student newStudent = studentService.saveStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id){
        Student student = studentService.getStudent(id);
        return new ResponseEntity<>(student, HttpStatus.FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id,
                                                 @RequestBody Student student){
        Student studentInDb = studentService.updateStudent(id, student);
        return new ResponseEntity<>(studentInDb, HttpStatus.OK);
    }
}
