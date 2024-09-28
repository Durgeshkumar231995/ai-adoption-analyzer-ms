package com.cst.sr.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
//@Document("UserInfo")
@Entity
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
