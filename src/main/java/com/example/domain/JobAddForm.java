package com.example.domain;

import java.util.Date;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class JobAddForm {
    @NotEmpty
    @Size(min=3, max=150)
    private String jobTitle;

    @NotEmpty
    @Size(min=2, max=250)
    private String jobDesc;
    
    @Min(1)
    private int numberPeopleToHire;

	@Future
    private Date lastApplicationDate;

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getJobDesc() {
		return jobDesc;
	}

	public void setJobDesc(String jobDesc) {
		this.jobDesc = jobDesc;
	}

	public int getNumberPeopleToHire() {
		return numberPeopleToHire;
	}

	public void setNumberPeopleToHire(int numberPeopleToHire) {
		this.numberPeopleToHire = numberPeopleToHire;
	}

	public Date getLastApplicationDate() {
		return lastApplicationDate;
	}

	public void setLastApplicationDate(Date lastApplicationDate) {
		this.lastApplicationDate = lastApplicationDate;
	}
 
}

