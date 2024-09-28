package com.cst.sr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst.sr.model.UserProfile;
import com.cst.sr.service.IUserProfileService;
import com.cst.sr.service.KafkaProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@RequestMapping("/api/ups")
@CrossOrigin("http://localhost:4200/")
public class UserProfileController {

	//http://localhost:8081/swagger-ui/index.html
	//http://vm-lab39-20-320.asia-south1-b.c.niit-stackroute-2.internal:8081/swagger-ui/index.html
	@Autowired
	private IUserProfileService userProfileService;
	
	@Autowired
    private KafkaProducer kafkaProducer;

	   

	@PostMapping("/register")
	public ResponseEntity<UserProfile> registerUserProfile(@RequestBody @Valid UserProfile userProfile) throws JsonProcessingException {
		log.info("PostMapping /register endpoint started");
		 String message = new ObjectMapper().writeValueAsString(userProfile);
		 kafkaProducer.sendMessage(message);
		UserProfile savedUserProfile = userProfileService.registerUserProfile(userProfile);
		return new ResponseEntity<>(savedUserProfile, HttpStatus.CREATED);
	}

//	@GetMapping("/UserProfileById/{userId}")
//	public ResponseEntity<UserProfile> getUserProfileById(@PathVariable Integer userId) {
//		log.info("GetMapping /UserProfileById endpoint started");
//		Optional<UserProfile> userProfile = userProfileService.getUserProfileById(userId);
//		return new ResponseEntity<>(userProfile.get(), HttpStatus.OK);
//
//	}

//	@PutMapping("/updateUserProfile/{userId}")
//	public ResponseEntity<UserProfile> updateUserProfile(@PathVariable Integer userId,@RequestBody @Valid UserProfile userProfile) {
//		log.info("PutMapping /updateUserProfile endpoint started");
//		UserProfile updatedUserProfile = userProfileService.updateUserProfile(userId, userProfile);
//		return new ResponseEntity<>(updatedUserProfile, HttpStatus.OK);
//
//	}

//	@PutMapping("/updatePassword/{userId}/{newPassword}")
//	public String updatePassword(@PathVariable("userId") Integer userId,@PathVariable("newPassword") String newPassword) {
//		log.info("PutMapping /updatePassword endpoint started");
//		userProfileService.updatePassword(userId, newPassword);
//		return "Password Updated";
//
//	}

	@DeleteMapping("/deleteUserProfile/{userId}")
	public String deleteUserProfile(@PathVariable Integer userId) {
		log.info("DeleteMapping /deleteUserProfile endpoint started");
		userProfileService.deleteUserProfile(userId);
		return "User Profile Deleted";

	}
}
