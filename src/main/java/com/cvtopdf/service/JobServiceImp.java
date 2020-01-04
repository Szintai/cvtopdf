package com.cvtopdf.service;


import com.cvtopdf.entity.Job;
import com.cvtopdf.exception.NoResourceException;
import com.cvtopdf.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImp implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImp(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @Override
    public Job save(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteById(Long id) {

        jobRepository.deleteById(id);
    }

    @Override
    public Job findById(Long id) {
        Optional<Job> optionalJob= jobRepository.findById(id);

        return optionalJob.map(o -> optionalJob.get()).orElseThrow(NoResourceException::new);

    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }
}