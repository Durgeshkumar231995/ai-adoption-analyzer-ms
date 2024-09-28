package com.cst.sr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst.sr.exception.DuplicateUserException;
import com.cst.sr.exception.UserNotFoundException;
import com.cst.sr.model.UserProfile;
import com.cst.sr.repository.UserProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserProfileServiceImpl implements IUserProfileService {

	@Autowired
	private UserProfileRepository userProfileRepository;
	
	

	@Override
	public UserProfile registerUserProfile(UserProfile userProfile) {
		log.info("inside registerUserProfile function in service layer");
		if (userProfileRepository.findByUsername(userProfile.getUsername()).isPresent()) {
			log.debug("*** inside findByUsername ***");
			throw new DuplicateUserException("Username already exists,try another username");
		}
		if (userProfileRepository.findByEmail(userProfile.getEmail()).isPresent()) {
			log.debug("*** inside findByEmail ***");
			throw new DuplicateUserException("Email already exists,try another email");
		}
		 userProfile.setPassword((userProfile.getPassword()));
		 UserProfile savedUserProfile = userProfileRepository.save(userProfile);
		
		return savedUserProfile;

	}

	/*
	 * @Override public Optional<UserProfile> getUserProfileById(Integer userId) {
	 * log.info("inside getUserProfileById function in service layer");
	 * 
	 * Optional<UserProfile> userProfile = userProfileRepository.findById(userId);
	 * 
	 * if(!userProfile.isPresent()) {
	 * log.debug("***getUserProfileById UserNotFoundException ***"); throw new
	 * UserNotFoundException("User with id " + userId + " does not exist."); }
	 * 
	 * return userProfile;
	 * 
	 * }
	 */
	/*
	 * @Override public UserProfile updateUserProfile(Integer userId, UserProfile
	 * userProfile) {
	 * log.info("inside updateUserProfile function in service layer"); if
	 * (!userProfileRepository.existsById(userId)) {
	 * log.debug("***updateUserProfile UserNotFoundException ***"); throw new
	 * UserNotFoundException("User with id " + userId + " does not exist."); } if
	 * (userProfileRepository.findByUsername(userProfile.getUsername()).isPresent()
	 * && !userProfileRepository.findById(userId).get().getUsername().equals(
	 * userProfile.getUsername())) {
	 * log.debug("***findByUsername , findById DuplicateUserException ***"); throw
	 * new DuplicateUserException("Username already exists."); } if
	 * (userProfileRepository.findByEmail(userProfile.getEmail()).isPresent() &&
	 * !userProfileRepository.findById(userId).get().getEmail().equals(userProfile.
	 * getEmail())) {
	 * log.debug("***findByEmail , findById DuplicateUserException ***"); throw new
	 * DuplicateUserException("Email already exists."); }
	 * userProfile.setUserId(userId); return
	 * userProfileRepository.save(userProfile); }
	 */
	/*
	 * @Override public void updatePassword(Integer userId, String newPassword) {
	 * log.info("inside updatePassword function in service layer"); UserProfile
	 * userProfile = userProfileRepository.findById(userId) .orElseThrow(() -> new
	 * UserNotFoundException("User with id " + userId + " does not exist."));
	 * userProfile.setPassword(newPassword);
	 * userProfileRepository.save(userProfile); }
	 */
	@Override
	public void deleteUserProfile(Integer userId) {
		log.info("inside deleteUserProfile function in service layer");
		if (!userProfileRepository.existsById(userId)) {
			 log.debug("***existsById UserNotFoundException ***");
			throw new UserNotFoundException("User with id " + userId + " does not exist.");
		}
		userProfileRepository.deleteById(userId);
	}

}