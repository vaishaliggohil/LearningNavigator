package com.practice.mysqlproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.mysqlproj.entites.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
