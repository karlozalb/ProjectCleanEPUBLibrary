package com.pcg.exceptions;

public class EPUBException extends Exception {

	private String message = null;
	 
    public EPUBException() {
        super();
    }
 
    public EPUBException(String message) {
        super(message);
        this.message = message;
    }
 
    public EPUBException(Throwable cause) {
        super(cause);
    }
 
    @Override
    public String toString() {
        return message;
    }
 
    @Override
    public String getMessage() {
        return message;
    }
	
}
