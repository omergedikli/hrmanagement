package com.example.service;

import com.example.domain.Application;
import com.example.domain.ApplicationForm;

public interface ApplicationService {
	Application basvur(ApplicationForm form, long jobId);
	Iterable<Application> getApplications();
	Application getAppById(long id);
}
