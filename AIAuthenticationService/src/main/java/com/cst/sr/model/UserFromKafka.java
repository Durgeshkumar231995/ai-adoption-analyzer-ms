package com.cst.sr.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserFromKafka {
	
	private Integer userId;

	private String firstName;

	private String lastName;

	private String username;

	private String password;

	private String email;

	private String mobileNumber;

	private String country;

	private String dateOfBirth;
}
