package com.practice.mysqlproj.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.mysqlproj.entites.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {


}
