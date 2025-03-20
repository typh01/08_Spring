package com.kh.spring.exception;

public class DuplicatedIdException extends RuntimeException {
	public DuplicatedIdException(String message) {
		super(message);
	}
}
