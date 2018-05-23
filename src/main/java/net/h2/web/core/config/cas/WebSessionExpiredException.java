package net.h2.web.core.config.cas;

import javax.xml.ws.WebFault;

@WebFault(name = "SessionExpiredException")
public class WebSessionExpiredException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public WebSessionExpiredException() {
		super("SessionExpiredException");
	}

}
