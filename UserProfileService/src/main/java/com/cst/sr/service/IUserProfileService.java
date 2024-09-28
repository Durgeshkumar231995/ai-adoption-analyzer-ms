package com.cst.sr.service;

import com.cst.sr.model.UserProfile;

public interface IUserProfileService {

	public UserProfile registerUserProfile(UserProfile userProfile);

//	public Optional<UserProfile> getUserProfileById(Integer userId);
//
//	public UserProfile updateUserProfile(Integer userId, UserProfile userProfile);
//
//	public void updatePassword(Integer userId, String newPassword);

	public void deleteUserProfile(Integer userId);

}
