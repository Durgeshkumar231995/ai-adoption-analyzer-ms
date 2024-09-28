package com.cst.sr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cst.sr.exception.ResourceNotFoundException;
import com.cst.sr.exception.UserNotFoundException;
import com.cst.sr.model.JobRoleBookmark;
import com.cst.sr.service.BookmarkServiceImpl;

@RestController
@RequestMapping("/v1/bs")
public class BookmarkServiceController {

	@Autowired
	BookmarkServiceImpl bookmarkServiceImpl;

	@GetMapping("/getBookmarkByEmail/{email}")
	public ResponseEntity<?> getBookmarkByEmail(@PathVariable String email) {
		try {
			List<JobRoleBookmark> byEmail = bookmarkServiceImpl.getByEmail(email);
			return ResponseEntity.ok(byEmail);

		} catch (ResourceNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}

	@GetMapping("/getAllJobRoleBookmark")
	public List<JobRoleBookmark> getAllJobRoleBookmark() {
		return bookmarkServiceImpl.getAllJobRoleBookmark();
	}

	@DeleteMapping("/deleteBookmarkById/{id}")
	public ResponseEntity<?> deleteBookmarkById(@PathVariable String id) {
		try {

			bookmarkServiceImpl.deleteById(id);
			return ResponseEntity.ok("User deleted with : " + id);

		} catch (UserNotFoundException e) {
			return ResponseEntity.ok(e.getMessage());
		}
	}
	
	@DeleteMapping("/deleteAllBookmark")
	public ResponseEntity<?> deleteAllBookmark() {

		bookmarkServiceImpl.deleteAllJobRoleBookmark();

		return ResponseEntity.ok("All JobRoleBookmark deleted : ");

	}

}
