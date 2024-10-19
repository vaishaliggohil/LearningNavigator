package com.practice.mysqlproj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mysqlproj.entites.Exam;
import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.repositories.ExamRepository;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Override
    public Exam createExam(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public Exam getExamById(Long examId) {
        return examRepository.findById(examId)
            .orElseThrow(() -> new RuntimeException("Exam not found with id: " + examId));
    }

    @Override
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    @Override
    public Exam updateExam(Long examId, Exam examDetails) {
        Exam exam = getExamById(examId);
        exam.setName(examDetails.getName());
        exam.setSubject(examDetails.getSubject());
        return examRepository.save(exam);
    }
    
    @Override
    public List<Student> getStudentsByExam(Long examId) {
        Exam exam = getExamById(examId);
        return exam.getStudents();
    }
    
    @Override
    public void deleteExam(Long examId) {
        examRepository.deleteById(examId);
    }

}
