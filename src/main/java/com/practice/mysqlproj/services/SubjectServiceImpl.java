package com.practice.mysqlproj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.entites.Subject;
import com.practice.mysqlproj.exception.SubjectNotFoundException;
import com.practice.mysqlproj.repositories.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService{


    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Subject createSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject getSubjectById(Long subjectId) throws SubjectNotFoundException {
        return subjectRepository.findById(subjectId)
                .orElseThrow(() -> new SubjectNotFoundException("Subject not found with ID: " + subjectId));
    }

    @Override
    public Subject updateSubject(Long subjectId, Subject updatedSubject) throws SubjectNotFoundException {
        Subject existingSubject = getSubjectById(subjectId);
        existingSubject.setName(updatedSubject.getName());
        return subjectRepository.save(existingSubject);
    }

    @Override
    public List<Student> getEnrolledStudents(Long subjectId) throws SubjectNotFoundException {
        Subject subject = getSubjectById(subjectId);
        return subject.getStudents(); 
    }

    @Override
    public void deleteSubject(Long subjectId) throws SubjectNotFoundException {
        Subject subject = getSubjectById(subjectId);
        subjectRepository.delete(subject);
    }

}
