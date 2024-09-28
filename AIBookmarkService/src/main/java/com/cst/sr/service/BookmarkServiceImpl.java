package com.cst.sr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cst.sr.exception.ResourceNotFoundException;
import com.cst.sr.exception.UserNotFoundException;
import com.cst.sr.model.JobRoleBookmark;
import com.cst.sr.repository.AIBookmarkRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class BookmarkServiceImpl implements IBookmarkService{

	@Autowired
	private AIBookmarkRepository aIBookmarkRepository;

	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<JobRoleBookmark> getByEmail(String email) {
		List<JobRoleBookmark> findByEmailJobRoleBookmark = aIBookmarkRepository.findByEmail(email);
		if(findByEmailJobRoleBookmark.size()<0 || findByEmailJobRoleBookmark==null|| findByEmailJobRoleBookmark.isEmpty()) {
		throw new  ResourceNotFoundException("email not exist oops..."+email);
		}
		return findByEmailJobRoleBookmark;
	}
	@Override
	public List<JobRoleBookmark> getAllJobRoleBookmark() {
		
		return aIBookmarkRepository.findAll();
	}
	@Override
	public void deleteById(String id) {
		Optional<JobRoleBookmark> existingJobRoleBookmark = aIBookmarkRepository.findById(id);
		if(!existingJobRoleBookmark.isPresent()) {
			throw new  UserNotFoundException("User Not Found Exception..."+id);
		}
		aIBookmarkRepository.deleteById(id);
	}
	@Override
	public void deleteAllJobRoleBookmark() {
		aIBookmarkRepository.deleteAll();
		
	}

	
}