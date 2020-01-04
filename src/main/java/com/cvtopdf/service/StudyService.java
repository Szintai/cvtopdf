package com.cvtopdf.service;



import com.cvtopdf.entity.Study;

import java.util.List;

public interface StudyService {

    Study save(Study study);

    void deleteById(Long id);

    Study findById(Long id);

    List<Study> findAll();


}
