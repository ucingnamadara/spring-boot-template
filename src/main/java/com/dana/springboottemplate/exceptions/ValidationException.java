package com.dana.springboottemplate.exceptions;

import lombok.Getter;

@Getter
public class ValidationException extends RuntimeException {

	private Object data;

	private String message;

	public ValidationException(String message) {
		super(message);
	}

}
