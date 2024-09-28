package com.cst.sr.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobRoleBookmark {

	@JsonProperty("id")
	private String id;
	
	@JsonProperty("Job_Title")
	private String jobTitle;

	@JsonProperty("Industry")
	private String industry;

	@JsonProperty("Company_Size")
	private String companySize;

	@JsonProperty("Location")
	private String location;

	@JsonProperty("AI_Adoption_Level")
	private String aiAdoptionLevel;

	@JsonProperty("Automation_Risk")
	private String automationRisk;

	@JsonProperty("Required_Skills")
	private String requiredSkills;

	@JsonProperty("Salary_USD")
	private Double salaryUSD;

	@JsonProperty("Remote_Friendly")
	private String remoteFriendly;

	@JsonProperty("Job_Growth_Projection")
	private String jobGrowthProjection;
	
	

}
