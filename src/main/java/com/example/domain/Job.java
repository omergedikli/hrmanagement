package com.example.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Job {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false, updatable=false)
	private long id;
	
    @Column(name = "jobtitle", nullable = false, updatable = false)
    private String jobTitle;

    @Column(name = "jobdescription", nullable = false)
    private String jobDesc;

	@Column(name = "numberPeopleToHire", nullable = false)
    private int numberPeopleToHire;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastApplicationDate;
     
    @OneToMany(mappedBy="job",cascade = CascadeType.ALL)
    private Set<Application> applications;
    
    
	public Set<Application> getApplications() {
		return applications;
	}
	
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public void setnumberPeopleToHire(int numberPeopleToHire) {
		this.numberPeopleToHire = numberPeopleToHire;
	}

	public Date getLastApplicationDate() {
		return lastApplicationDate;
	}

	public void setLastApplicationDate(Date lastApplicationDate) {
		this.lastApplicationDate = lastApplicationDate;
	}
   
    public Job() {}

    public Job(String jobtitle, String jobdescription, int numberhire,Date lastApplicationDate) {
        this.jobTitle = jobtitle;
        this.jobDesc= jobdescription;
        this.numberPeopleToHire = numberhire;
        this.lastApplicationDate = lastApplicationDate;
    }
    
    public String toString() {
        return "Job{" +
                "Id:" + id +
                ", Title:'" + jobTitle + '\'' +
                ", Desc:'" + jobDesc + '\'' +
                ", Number of People to Hire:'" + numberPeopleToHire + '\'' +
                ", Last Application Date:'" + lastApplicationDate + '\'' +
                '}';
    }
	
}
