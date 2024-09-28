package com.cst.sr.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class JobRoleBookmark implements Serializable{

	
	private static final long serialVersionUID = 1L;

	 @Id
	private String id;
	
	
	private String jobTitle;

	
	private String industry;

	
	private String companySize;

	
	private String location;

	
	private String aiAdoptionLevel;

	
	private String automationRisk;

	
	private String requiredSkills;

	
	private Double salaryUSD;

	
	private String remoteFriendly;

	
	private String jobGrowthProjection;
	
	
	private String email;
	
	

}
