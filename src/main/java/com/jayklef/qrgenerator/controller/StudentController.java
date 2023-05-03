package com.jayklef.qrgenerator.controller;

import com.google.zxing.WriterException;
import com.jayklef.qrgenerator.model.Student;
import com.jayklef.qrgenerator.service.StudentService;
import com.jayklef.qrgenerator.utility.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    @Autowired
    private StudentService studentService;
    @GetMapping("/all")
    public ResponseEntity<List<Student>> getAllStudent() throws IOException, WriterException {
        List<Student> students = studentService.getAllStudents();

        if (students.size() != 0){
            for (Student student: students){
                QRCodeGenerator.generateQrCode(student);
            }
        }
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
