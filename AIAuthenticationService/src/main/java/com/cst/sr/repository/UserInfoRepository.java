package com.cst.sr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cst.sr.model.UserInfo;
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{
	
	 UserInfo findByUsernameOrEmail(String email,String username);
	 
//	    @Query("{ '$or': [ { 'username': ?0 }, { 'email': ?1 } ] }")
//	    UserInfo findByUsernameOrEmail(String username, String email);
	   

}
