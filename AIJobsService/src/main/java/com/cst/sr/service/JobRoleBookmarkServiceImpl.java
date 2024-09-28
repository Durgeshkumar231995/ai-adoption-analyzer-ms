package com.cst.sr.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cst.sr.model.JobRoleBookmark;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobRoleBookmarkServiceImpl implements IJobRoleBookmarkService {

	private static final String BASE_URL = "http://localhost:3232/aijobs";
	
	ObjectMapper objectMapper=new ObjectMapper();

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public JobRoleBookmark[] getAllJobRoleBookmark() {

		log.info("inside getAllJobRoleBookmark function in service layer");
		JobRoleBookmark[] forObject = restTemplate.getForObject(BASE_URL, JobRoleBookmark[].class);

		return forObject;
		
	}


	@Override
	public JobRoleBookmark getAIJobRoleBookmarkById(String Id) {

		log.info("inside getAllJobRoleBookmark function in service layer");
		JobRoleBookmark forObject = restTemplate.getForObject(BASE_URL+"/"+Id, JobRoleBookmark.class);

		return forObject;
		
	}

	@Override
	public List<JobRoleBookmark> getJobsByProperty(String propertyName, String value)
			throws JsonMappingException, JsonProcessingException {
		log.info("inside getJobsByProperty function in service layer");
		String url = String.format("%s?%s=%s", BASE_URL, propertyName, value);
		
		ResponseEntity<JobRoleBookmark[]> response = restTemplate.getForEntity(url, JobRoleBookmark[].class);

		List<JobRoleBookmark> asList = Arrays.asList(response.getBody());

		return asList;
	}



	

}
