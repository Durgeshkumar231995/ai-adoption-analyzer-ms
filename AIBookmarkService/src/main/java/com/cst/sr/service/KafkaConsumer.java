package com.cst.sr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cst.sr.model.JobRoleBookmark;
import com.cst.sr.repository.AIBookmarkRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class KafkaConsumer {
	
	
	@Autowired
	private AIBookmarkRepository aiBookmarkRepository;
	
	@PersistenceContext
    private EntityManager entityManager;

	//@CachePut(value = "bookmarkItems", key = "#bookmarkItems.id")
	@KafkaListener(topics = "Bookmark_AIJob", groupId = "group_id")
	public void consume(String message) throws JsonMappingException, JsonProcessingException {
		System.out.println("Consumer message"+message);
		JobRoleBookmark user = new ObjectMapper().readValue(message, JobRoleBookmark.class);
		//entityManager.flush();
        //entityManager.clear();
		aiBookmarkRepository.save(user);
	}
}