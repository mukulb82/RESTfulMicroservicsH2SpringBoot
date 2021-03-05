package com.embltest.embl.exception;

public class ManagePersonException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ManagePersonException(String message) {
		super(message);

	}

	public ManagePersonException(String message, Throwable cause) {
		super(message, cause);
	}

	public ManagePersonException(Throwable cause) {
		super(cause);
	}

}
