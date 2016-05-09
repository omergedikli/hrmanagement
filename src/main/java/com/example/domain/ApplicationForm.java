package com.example.domain;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import javax.validation.constraints.Size;


public class ApplicationForm {

	@NotEmpty
    @Size(min=2, max=250)
	private String name;
	
    @NotEmpty
	@Email (message = "Email format is not valid")	//Email format validation
	private String email;

	@Size(min=11)
    @NotEmpty
	private String phone;
	
    @NotEmpty
	private String address;

    @NotEmpty
    private String thoughts;

    private MultipartFile file;
		 
	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
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

}
