package com.cst.sr.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public String message;
	
}
