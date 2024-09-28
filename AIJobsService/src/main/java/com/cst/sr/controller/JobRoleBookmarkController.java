package com.cst.sr.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cst.sr.model.JobRoleBookmark;
import com.cst.sr.model.JobRoleBookmarkDTO;
import com.cst.sr.service.IJobRoleBookmarkService;
import com.cst.sr.service.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/v1/AIJob")
@CrossOrigin("*")
public class JobRoleBookmarkController {

	// http://localhost:9080/swagger-ui/index.html
	

	@Autowired
	private IJobRoleBookmarkService iJobRoleBookmarkService;
	
	@Autowired
    private KafkaProducer kafkaProducer;
	
	@Autowired
    private ModelMapper modelMapper;

	@GetMapping("/getAllRoles")
	public JobRoleBookmark[] getAllRoles() {
		log.info("getAllRoles endpoint started in controller");
		JobRoleBookmark[] allJobRoleBookmark = iJobRoleBookmarkService.getAllJobRoleBookmark();
		System.out.println(allJobRoleBookmark.toString());
		return allJobRoleBookmark;
	}
	

	
	@GetMapping("/getAllRoles/{Id}")
	public JobRoleBookmark getAllRoles(@PathVariable String Id) {
		log.info("getAllRoles endpoint started in controller");
		JobRoleBookmark allJobRoleBookmark = iJobRoleBookmarkService.getAIJobRoleBookmarkById(Id);
		System.out.println(allJobRoleBookmark.toString());
		return allJobRoleBookmark;
	}

	@GetMapping("/JobRoleBookmark/filter")
	public List<JobRoleBookmark> getRolesByProperty(@RequestParam String propertyName, @RequestParam String value)
			throws JsonMappingException, JsonProcessingException {
		log.info("JobRoleBookmark/filter endpoint started in controller");
		return iJobRoleBookmarkService.getJobsByProperty(propertyName, value);
	}
	
	
	@PostMapping("saveBookmark/{email}")
	public void sendDTO(@PathVariable String email, @RequestBody JobRoleBookmarkDTO bookmarkDTO)
			throws JsonProcessingException {

		JobRoleBookmark jobRoleBookmark = iJobRoleBookmarkService
				.getAIJobRoleBookmarkById(bookmarkDTO.getId());
		  
		JobRoleBookmarkDTO jobRoleBookmarkDTO = modelMapper.map(jobRoleBookmark, JobRoleBookmarkDTO.class);

		jobRoleBookmarkDTO.setEmail(email);

		String message = new ObjectMapper().writeValueAsString(jobRoleBookmarkDTO);

		kafkaProducer.sendMessage(message);
	}

	
	
}
