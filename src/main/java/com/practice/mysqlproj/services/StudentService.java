package com.practice.mysqlproj.services;

import java.util.Optional;
import java.util.List;
import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.exception.ExamNotFoundException;
import com.practice.mysqlproj.exception.StudentNotFoundException;
import com.practice.mysqlproj.exception.SubjectNotFoundException;

public interface StudentService {

    Student registerStudent(Student student);

    List<Student> getAllStudents();

    Student updateStudent(Long studentId, Student updatedStudentDetails);

    Optional<Student> findStudentById(Long studentId) throws StudentNotFoundException;

    Student enrollStudentInSubject(Long studentId, Long subjectId) throws StudentNotFoundException, SubjectNotFoundException;

    Student registerStudentForExam(Long studentId, Long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotFoundException;

    void deleteStudent(Long studentId) throws StudentNotFoundException;

}
