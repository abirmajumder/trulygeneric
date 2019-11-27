package com.trulygeneric.common.exception;

public class ApplicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ApplicationException() {
		super();
	}

	public ApplicationException( String msg ) {
		super(msg);
	}
	
	public ApplicationException( Throwable err, String msg ) {
		super(msg, err);
	}
	
}
