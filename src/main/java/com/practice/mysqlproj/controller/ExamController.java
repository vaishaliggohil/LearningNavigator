package com.practice.mysqlproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.mysqlproj.entites.Exam;
import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.exception.ExamNotFoundException;
import com.practice.mysqlproj.exception.SubjectNotFoundException;
import com.practice.mysqlproj.services.ExamService;
import com.practice.mysqlproj.services.NumberService;


@RestController
@RequestMapping("/exams")
public class ExamController {
   
    @Autowired
    private ExamService examService;

    @Autowired
    private NumberService numberService;

     @PostMapping
    public ResponseEntity<Exam> createExam(@RequestBody Exam exam) throws SubjectNotFoundException {
        Exam examE = examService.createExam(exam);
        return ResponseEntity.ok().body(examE);
    }

    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable(value = "examId") long examId) throws ExamNotFoundException {
        Exam exam = examService.getExamById(examId);
        return ResponseEntity.ok().body(exam);
    }

    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok().body(exams);
    }


    @DeleteMapping("/{examId}")
    public ResponseEntity<String> deleteExam(@PathVariable long examId) throws ExamNotFoundException {
        String message = "Successfully deleted exam with ID: " + String.valueOf(examId);
        examService.deleteExam(examId);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{examId}/students")
    public ResponseEntity<List<Student>> getStudentsByExam(@PathVariable Long examId) {
        List<Student> students = examService.getStudentsByExam(examId);
        return ResponseEntity.ok().body(students);
    }

    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<String> getNumberFact(@PathVariable int number) {
        String fact = numberService.getNumberFact(number);
        return new ResponseEntity<>(fact, HttpStatus.OK);
    }
    
}
