package com.cst.sr.service;

import java.util.List;

import com.cst.sr.model.JobRoleBookmark;

public interface IBookmarkService {

	
	public List<JobRoleBookmark> getByEmail(String email);
	
	public List<JobRoleBookmark> getAllJobRoleBookmark();
	
	public void deleteById(String id);
	
	public void deleteAllJobRoleBookmark();
}