package com.scl.devnest.exceptions;

import com.scl.devnest.enums.ErrorCode;

public class DevnestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private ErrorCode code;
	
	public DevnestException(ErrorCode code) {
		super(code.name());
	}

	public ErrorCode getCode() {
		return code;
	}
}
