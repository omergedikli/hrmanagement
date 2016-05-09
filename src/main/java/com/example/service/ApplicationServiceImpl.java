package com.example.service;

import com.example.domain.Application;
import com.example.domain.ApplicationForm;
import com.example.repository.ApplicationRepository;
import com.example.repository.JobRepository;
import com.example.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    private final ApplicationRepository applicationRepository;
    private final JobRepository jobRepository;

    @Autowired
    public ApplicationServiceImpl(ApplicationRepository applicationRepository, JobRepository jobRepository) {
        this.applicationRepository = applicationRepository;
        this.jobRepository = jobRepository;
    }

    public Application basvur(ApplicationForm form, long jobid) {
        Application app = new Application(form.getName(),form.getEmail(),form.getPhone(),form.getAddress(),form.getThoughts());
        System.out.print(app.toString()); 
        app.setJob(jobRepository.findOne(jobid));
        return applicationRepository.save(app);
    }

    public Application getAppById(long id) {
        return applicationRepository.findOne(id);
    }
    
    @Override
    public Iterable<Application> getApplications() {
        return applicationRepository.findAll();
    }
 }