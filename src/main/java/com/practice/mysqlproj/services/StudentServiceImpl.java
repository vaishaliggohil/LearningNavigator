package com.practice.mysqlproj.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.mysqlproj.entites.Exam;
import com.practice.mysqlproj.entites.Student;
import com.practice.mysqlproj.entites.Subject;
import com.practice.mysqlproj.exception.ExamNotFoundException;
import com.practice.mysqlproj.exception.StudentNotFoundException;
import com.practice.mysqlproj.exception.SubjectNotFoundException;
import com.practice.mysqlproj.repositories.ExamRepository;
import com.practice.mysqlproj.repositories.StudentRepository;
import com.practice.mysqlproj.repositories.SubjectRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ExamRepository examRepository;
    
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public Student registerStudent(Student student) {
       Student studentResult = studentRepository.save(student);
       return studentResult;
    }

    @Override
    public Optional<Student> findStudentById(Long studentId) throws StudentNotFoundException {
      Optional<Student> student = studentRepository.findById(studentId);
      return student;
    }

    @Override
    public List<Student> getAllStudents() {
      return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long studentId, Student updatedStudentDetails) {
      Optional<Student> studentOptional = studentRepository.findById(studentId);

      if (!studentOptional.isPresent()) {
          throw new RuntimeException("Student not found with ID: " + studentId);
      }

      Student existingStudent = studentOptional.get();
      existingStudent.setName(updatedStudentDetails.getName());

      return studentRepository.save(existingStudent);
    }

    @Override
    public Student enrollStudentInSubject(Long studentId, Long subjectId) throws StudentNotFoundException, SubjectNotFoundException {
      Student student = findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found!"));
      
      Subject subject = subjectRepository.findById(studentId).orElseThrow(() -> new SubjectNotFoundException("Subject Not Found!"));

      List<Subject> enrolledSubjects = student.getSubjects();
      if (!enrolledSubjects.contains(subject)) {
          enrolledSubjects.add(subject);
      }

      return studentRepository.save(student);
    }

    @Override
    public Student registerStudentForExam(Long studentId, Long examId) throws StudentNotFoundException, ExamNotFoundException, SubjectNotFoundException {

      Student student = findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found!"));
      
      Exam exam = examRepository.findById(examId)
              .orElseThrow(() -> new ExamNotFoundException("Exam not found with ID: " + examId));

      if (!student.getSubjects().contains(exam.getSubject())) {
          throw new SubjectNotFoundException("Student must be enrolled in the subject before registering for the exam.");
      }

      List<Exam> registeredExams = student.getExams();
      if (!registeredExams.contains(exam)) {
          registeredExams.add(exam);
      }
      return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) throws StudentNotFoundException {
      Student student = findStudentById(studentId).orElseThrow(() -> new StudentNotFoundException("Student Not Found!"));
      studentRepository.delete(student);
    }
    
   
}
