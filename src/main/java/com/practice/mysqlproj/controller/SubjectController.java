package com.practice.mysqlproj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.practice.mysqlproj.entites.Subject;
import com.practice.mysqlproj.exception.SubjectNotFoundException;
import com.practice.mysqlproj.services.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody Subject subject) {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.ok().body(createdSubject);
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok().body(subjects);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long subjectId) throws SubjectNotFoundException {
        Subject subject = subjectService.getSubjectById(subjectId);
        return ResponseEntity.ok().body(subject);
    }

    @PutMapping("/{subjectId}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long subjectId, @RequestBody Subject updatedSubject) throws SubjectNotFoundException {
        Subject subject = subjectService.updateSubject(subjectId, updatedSubject);
        return ResponseEntity.ok().body(subject);
    }

    @GetMapping("/{subjectId}/students")
    public ResponseEntity<List<Student>> getEnrolledStudents(@PathVariable Long subjectId) throws SubjectNotFoundException {
        List<Student> students = subjectService.getEnrolledStudents(subjectId);
        return ResponseEntity.ok().body(students);
    }

    @DeleteMapping("/{subjectId}")
    public ResponseEntity<String> deleteSubject(@PathVariable Long subjectId) throws SubjectNotFoundException {
        String message = "Successfully deleted subject with ID: " + String.valueOf(subjectId);
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.ok().body(message);
    }

}
