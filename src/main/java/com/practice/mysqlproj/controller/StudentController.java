package com.practice.mysqlproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.exception.ExamNotFoundException;
import com.practice.mysqlproj.exception.StudentNotFoundException;
import com.practice.mysqlproj.exception.SubjectNotFoundException;
import com.practice.mysqlproj.services.NumberService;
import com.practice.mysqlproj.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

     @Autowired
    private NumberService numberService;

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student registerStudent) throws StudentNotFoundException {
        Student student = studentService.registerStudent(registerStudent);
        return ResponseEntity.ok().body(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable(value = "studentId") Long studentId) throws StudentNotFoundException {
        Student student = studentService.findStudentById(studentId) .orElseThrow(() -> new StudentNotFoundException("Student Not Found!"));
        return ResponseEntity.ok().body(student);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(
            @PathVariable Long studentId, 
            @RequestBody Student updatedStudentDetails) {

        Student updatedStudent = studentService.updateStudent(studentId, updatedStudentDetails);
         return ResponseEntity.ok().body(updatedStudent);
    }

    @PostMapping("/{studentId}/subjects/{subjectId}")
    public ResponseEntity<Student> enrollStudentInSubject(@PathVariable Long studentId, @PathVariable Long subjectId) throws StudentNotFoundException, SubjectNotFoundException {
        Student student = studentService.enrollStudentInSubject(studentId, subjectId);
        return ResponseEntity.ok().body(student);
    }

    @PostMapping("/{studentId}/exams/{examId}")
    public ResponseEntity<Student> registerStudentForExam(@PathVariable Long studentId, @PathVariable Long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotFoundException {
        Student student = studentService.registerStudentForExam(studentId, examId);
        return ResponseEntity.ok().body(student);
    }
    
    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId) throws StudentNotFoundException {
        String message = "Successfully deleted student with ID: " + String.valueOf(studentId);
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String fact = numberService.getNumberFact(number);
        return new ResponseEntity<>(fact, HttpStatus.OK);
    }
}
