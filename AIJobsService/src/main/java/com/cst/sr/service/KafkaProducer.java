package com.cst.sr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
	
	@Autowired
	private  KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String TOPIC = "Bookmark_AIJob";

	public void sendMessage(String message) {
		System.out.println("producer message"+message);
		kafkaTemplate.send(TOPIC, message);
	}
}