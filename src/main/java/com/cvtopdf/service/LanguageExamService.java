package com.cvtopdf.service;

import com.cvtopdf.entity.LanguageExam;



public interface LanguageExamService {

    LanguageExam save(LanguageExam languageExam);

    void deleteById(Long id);

    LanguageExam findById(Long id);

}
