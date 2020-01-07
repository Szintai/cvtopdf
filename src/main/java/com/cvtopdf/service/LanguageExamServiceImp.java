package com.cvtopdf.service;


import com.cvtopdf.entity.LanguageExam;
import com.cvtopdf.exception.NoResourceException;
import com.cvtopdf.repository.LanguageExamRepository;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class LanguageExamServiceImp implements LanguageExamService {

    private final LanguageExamRepository languageExamRepository;

    public LanguageExamServiceImp(LanguageExamRepository languageExamRepository) {
        this.languageExamRepository = languageExamRepository;
    }

    @Override
    public LanguageExam save(LanguageExam languageExam) {

        return languageExamRepository.save(languageExam);
    }

    @Override
    public void deleteById(Long id) {
        languageExamRepository.deleteById(id);
    }

    @Override
    public LanguageExam findById(Long id) {
        Optional<LanguageExam> optionalLanguageExam= languageExamRepository.findById(id);

        return optionalLanguageExam.map(o -> optionalLanguageExam.get()).orElseThrow(NoResourceException::new);
    }


}
