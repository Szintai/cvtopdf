package com.cvtopdf.service;


import com.cvtopdf.entity.Study;
import com.cvtopdf.exception.NoResourceException;
import com.cvtopdf.repository.StudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudyServiceImp implements StudyService {

    private final StudyRepository studyRepository;

    public StudyServiceImp(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    @Override
    public Study save(Study study) {
        return studyRepository.save(study);
    }

    @Override
    public void deleteById(Long id) {

        studyRepository.deleteById(id);
    }

    @Override
    public Study findById(Long id) {
        Optional<Study> optionalStudy= studyRepository.findById(id);

        return optionalStudy.map(o -> optionalStudy.get()).orElseThrow(NoResourceException::new);

    }

    @Override
    public List<Study> findAll() {
        return studyRepository.findAll();
    }
}