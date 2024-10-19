package com.practice.mysqlproj.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.mysqlproj.entites.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findBySubject_SubjectId(Long subjectId);
}
