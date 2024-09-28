package com.cst.sr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cst.sr.model.JobRoleBookmark;


@Repository
public interface AIBookmarkRepository extends JpaRepository<JobRoleBookmark,String>{

	List<JobRoleBookmark> findByEmail(String email);
}
