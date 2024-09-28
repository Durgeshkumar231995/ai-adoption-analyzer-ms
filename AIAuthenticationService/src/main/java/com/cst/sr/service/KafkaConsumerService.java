package com.cst.sr.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.cst.sr.model.UserFromKafka;
import com.cst.sr.model.UserInfo;
import com.cst.sr.repository.UserInfoRepository;
import com.cst.sr.utils.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumerService {

    @Autowired
    private UserInfoRepository userInfoRepository;

   @Autowired
   private JwtUtil jwtUtil;
   
   ModelMapper modelMapper = new ModelMapper();
   
    @KafkaListener(topics = "user_registration", groupId = "group_id")
    public void consume(String userInfo) throws JsonMappingException, JsonProcessingException {
        // Save the credentials securely
    	UserFromKafka userFromKafka = new ObjectMapper().readValue(userInfo, UserFromKafka.class);
    	UserInfo userInfo2 = modelMapper.map(userFromKafka, UserInfo.class);
    	log.info("**UserInfo***: " + userInfo2);
    	userInfoRepository.save(userInfo2);
        
        // Generate JWT token
        String token = jwtUtil.generateToken(userInfo2.getUsername());
        log.info("JWT Token: " + token);
        // For demonstration purposes, print the token
        System.out.println("Generated JWT Token: " + token);
    }
}