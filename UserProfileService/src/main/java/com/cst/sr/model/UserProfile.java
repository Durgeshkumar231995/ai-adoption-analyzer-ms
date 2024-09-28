package com.cst.sr.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;
	
	@NotBlank(message = "firstName must not be blank")
	@Size(min = 3, max = 50, message = "firstName must be at most 3 min & max 50 characters")
	private String firstName;
	
	@NotBlank(message = "lastName must not be blank")
	@Size(min = 3, max = 50, message = "lastName must be at most 3 min & max 50 characters")
	private String lastName;
	
	@NotBlank(message = "Username must not be blank")
	@Size(min = 5, max = 15, message = "Username must be at most 5 min & max 15 characters")
	private String username;
	
	@NotNull
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$]).{8,}$", message = "Password must be at least 8 characters long, include one uppercase letter, one lowercase letter, one digit, and one special character (@#$).")
	private String password;
	
	@NotNull
	@Email(message = "Email should be valid")
	private String email;

	@NotNull
	@Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits long")
	private String mobileNumber;
	
	private String country;
	
	@NotNull
	private String dateOfBirth;


	

}
