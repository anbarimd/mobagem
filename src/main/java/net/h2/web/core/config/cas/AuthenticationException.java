package net.h2.web.core.config.cas;

import javax.xml.ws.WebFault;

@WebFault(name = "AuthenticationException")
public class AuthenticationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public AuthenticationException() {
		super("AuthenticationException");
	}

}
