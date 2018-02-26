package com.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Job {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)	
	@Column(name="job_id")
private int id;
private String jobTitle;
private String jobDescription;
private String skillsRequired;
private String location;
private String yearsofExperience;
private String salary;
private Date postedon;

	
	
	
}
