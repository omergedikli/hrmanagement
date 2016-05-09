package com.example.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Application {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(nullable=false, updatable=false)
	private long id;
	
    @Column(name = "name", nullable = false, updatable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

	@Column(name = "phone", nullable = false)
    private String phone;

	@Column(name = "address", nullable = false)
    private String address;

	@Column(name = "thoughts", nullable = false)
    private String thoughts;

    @ManyToOne(fetch = FetchType.LAZY)
	private Job job;
    
    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getThoughts() {
		return thoughts;
	}

	public void setThoughts(String thoughts) {
		this.thoughts = thoughts;
	}
 
	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Application() {}

    public Application(String name, String email, String phone,String address,String thoughts) {
    	this.name = name;
        this.email= email;
        this.phone = phone;
        this.address = address;
        this.thoughts = thoughts;
    }
    
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", Name='" + name + '\'' +
                ", Email='" + email + '\'' +
                ", Phone='" + phone + '\'' +
                '}';
    }
	
}
