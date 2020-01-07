package com.cvtopdf.repository;


import com.cvtopdf.entity.LanguageExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageExamRepository extends JpaRepository<LanguageExam, Long> {


}
