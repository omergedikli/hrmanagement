package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.JobAddForm;
import com.example.repository.JobRepository;
import com.example.domain.Job;

@Service
public class JobServiceImpl implements JobService {
    private final JobRepository jobrepository;

    @Autowired
    public JobServiceImpl(JobRepository joblistingrepository) {
          this.jobrepository = joblistingrepository;
    }

    public Job addJob(JobAddForm form) {
            Job job = new Job(form.getJobTitle(), form.getJobDesc(), form.getNumberPeopleToHire(), form.getLastApplicationDate());
            System.out.print(job.toString()); 
            return jobrepository.save(job);
    }
     
    @Override
    public Iterable<Job> getJobs() {
        return jobrepository.findAll();
    }
     
    @Override
    public Job getJobById(long id) {
    	return jobrepository.findOne(id);
    }
    
    @Override
    public void deleteJobById(long id) {
    	jobrepository.delete(id);
    }
 
}
