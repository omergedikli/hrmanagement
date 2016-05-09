package com.example.service;

import com.example.domain.Job;
import com.example.domain.JobAddForm;

public interface JobService {
	Job addJob(JobAddForm form);
	Iterable<Job> getJobs();
	Job getJobById(long id);
    void deleteJobById(long id);
}
