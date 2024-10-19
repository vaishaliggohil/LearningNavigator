package com.practice.mysqlproj.services;

import java.util.List;

import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.entites.Subject;
import com.practice.mysqlproj.exception.SubjectNotFoundException;

public interface  SubjectService {

    Subject createSubject(Subject subject);

    List<Subject> getAllSubjects();

    Subject getSubjectById(Long subjectId) throws SubjectNotFoundException ;

    Subject updateSubject(Long subjectId, Subject updatedSubject) throws SubjectNotFoundException;

    List<Student> getEnrolledStudents(Long subjectId) throws SubjectNotFoundException;

    void deleteSubject(Long subjectId) throws SubjectNotFoundException;

}
