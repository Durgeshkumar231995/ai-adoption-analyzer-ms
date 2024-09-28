package com.cst.sr.controller;

import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.jcajce.provider.digest.GOST3411.HashMac;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cst.sr.model.UserInfo;
import com.cst.sr.repository.UserInfoRepository;
import com.cst.sr.utils.JwtUtil;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin("http://localhost:4200/")
public class AuthenticationController {

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	

	@PostMapping("/login")
	public ResponseEntity<Map<String,String>> login(@RequestBody UserInfo userInfo) {
		
		
		// Validate user credentials
		UserInfo existingUser = userInfoRepository.findByUsernameOrEmail(userInfo.getEmail(), userInfo.getUsername());
		System.out.println("***********"+existingUser);
		if (existingUser != null && existingUser.getPassword().equals(userInfo.getPassword())) {
			// If valid, generate JWT token
			//return jwtUtil.generateToken(userInfo.getUsername());
			Map<String,String> token=new HashMap<String,String>();
			token.put("token", jwtUtil.generateToken(userInfo.getUsername()));
			return new ResponseEntity<Map<String,String>>(token,HttpStatus.OK);
			
		} else {
			
			throw new RuntimeException("Invalid credentials");
		}
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam String token) {
		return jwtUtil.validateToken(token);
	}
	
//	@GetMapping("/find/{username}/{email}")
//    public UserInfo findByUsernameOrEmail(@PathVariable  String username,
//    		@PathVariable String email) {
//        return userInfoRepository.findByUsernameOrEmail(username, email);
//    }
	
	

}
