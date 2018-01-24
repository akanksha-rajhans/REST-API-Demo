package org.restDemo.demo.messengerApp.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1160995423537235651L;
	
	public DataNotFoundException(String message) {
		super(message);
	}
	
}
