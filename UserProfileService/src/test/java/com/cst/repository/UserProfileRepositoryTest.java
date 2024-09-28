package com.cst.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.cst.sr.model.UserProfile;
import com.cst.sr.repository.UserProfileRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserProfileRepositoryTest {

	@Autowired
	 UserProfileRepository userProfileRepository;

	@Test
	public void SaveUser() {

		// Add test data
		UserProfile userProfile = new UserProfile(1,"Durgesh", "Gupta", "Durgesh12345", "Durgesh@12345",
				"Durgesh@gmail.com", "9876678977", "INDIA", "23-09-1995");

		UserProfile save = userProfileRepository.save(userProfile);
		System.out.println(")))))"+save);
	}

	@Test
	public void whenFindByUsername_thenReturnUserProfile() {
		// When
		Optional<UserProfile> found = userProfileRepository.findByUsername("Durgesh12345");

		// Then
		assertThat(found).isPresent();
		assertThat(found.get().getUsername()).isEqualTo("Durgesh12345");
	}

	@Test
	public void whenFindByEmail_thenReturnUserProfile() {
		// When
		Optional<UserProfile> found = userProfileRepository.findByEmail("Durgesh@gmail.com");

		// Then
		assertThat(found).isPresent();
		assertThat(found.get().getEmail()).isEqualTo("Durgesh@gmail.com");
	}

	@Test
	public void whenFindByUsername_notFound() {
		// When
		Optional<UserProfile> found = userProfileRepository.findByUsername("Amit12345");

		// Then
		assertThat(found).isNotPresent();
	}

	@Test
	public void whenFindByEmail_notFound() {
		// When
		Optional<UserProfile> found = userProfileRepository.findByEmail("Sachin@example.com");

		// Then
		assertThat(found).isNotPresent();
	}
}