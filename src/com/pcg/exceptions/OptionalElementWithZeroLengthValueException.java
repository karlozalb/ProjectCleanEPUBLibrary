package com.pcg.exceptions;

public class OptionalElementWithZeroLengthValueException extends EPUBException{

	public OptionalElementWithZeroLengthValueException() {
		super();
	}

	public OptionalElementWithZeroLengthValueException(String message) {
		super(message);
	}

	public OptionalElementWithZeroLengthValueException(Throwable cause) {
		super(cause);
	}

}
