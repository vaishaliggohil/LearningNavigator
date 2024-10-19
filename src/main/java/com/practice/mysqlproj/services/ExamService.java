package com.practice.mysqlproj.services;

import java.util.List;

import com.practice.mysqlproj.entites.Exam;
import com.practice.mysqlproj.entites.Student;

public interface ExamService {
    
    Exam createExam(Exam exam);

    Exam getExamById(Long examId);

    List<Exam> getAllExams();

    Exam updateExam(Long examId, Exam examDetails);

    List<Student> getStudentsByExam(Long examId);

    void deleteExam(Long examId);

    
    
}
