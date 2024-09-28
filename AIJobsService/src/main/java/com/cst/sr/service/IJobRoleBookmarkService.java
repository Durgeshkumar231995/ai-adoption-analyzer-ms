package com.cst.sr.service;

import java.util.List;

import com.cst.sr.model.JobRoleBookmark;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface IJobRoleBookmarkService {

	public JobRoleBookmark[] getAllJobRoleBookmark();
	public JobRoleBookmark getAIJobRoleBookmarkById(String Id);

	public List<JobRoleBookmark> getJobsByProperty(String propertyName, String value)throws JsonMappingException, JsonProcessingException;

	

}
