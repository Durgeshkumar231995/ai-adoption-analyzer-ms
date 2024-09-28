package com.cst.sr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobRoleBookmarkDTO {

	
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
