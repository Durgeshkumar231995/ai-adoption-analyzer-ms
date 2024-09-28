package com.cst.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.cst.sr.model.UserProfile;
import com.cst.sr.repository.UserProfileRepository;
import com.cst.sr.service.UserProfileServiceImpl;

@SpringBootTest
class UserProfileServiceImplTest {

    @InjectMocks
    private UserProfileServiceImpl userProfileService;

    @Mock
    private UserProfileRepository userProfileRepository;
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testRegisterUserProfile_Success() {
    	
    			UserProfile userProfile = new UserProfile(1,"Durgesh", "Gupta", "Durgesh12345", "Durgesh@12345",
    					"Durgesh@gmail.com", "9876678977", "INDIA", "23-09-1995");
        // Mock repository methods
        when(userProfileRepository.findByUsername(any(String.class))).thenReturn(Optional.empty());
        when(userProfileRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
        when(userProfileRepository.save(any(UserProfile.class))).thenReturn(userProfile);

        UserProfile result = userProfileService.registerUserProfile(userProfile);
        assertNotNull(result);
        assertThat(result.getUserId()).isNotNull();
        assertEquals("Durgesh12345", result.getUsername());
        verify(userProfileRepository, times(1)).save(userProfile);
    }
    
   
   

}
