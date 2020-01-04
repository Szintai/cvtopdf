package com.cvtopdf.service;


import com.cvtopdf.entity.Job;

import java.util.List;

public interface JobService {

    Job save(Job job);

    void deleteById(Long id);

    Job findById(Long id);

    List<Job> findAll();


}
