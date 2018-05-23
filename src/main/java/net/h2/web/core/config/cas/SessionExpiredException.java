package net.h2.web.core.config.cas;

import java.io.Serializable;

public final class SessionExpiredException extends RuntimeException implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static final String DEFAULT_MESSAGE = "Session Expired"
			+ ", please click the refresh button on your browser.";

	/**
	 * Constructor used by RPC serialization. Note that the client side code
	 * will always get a generic error message.
	 */
	public SessionExpiredException() {
		super(DEFAULT_MESSAGE);
	}

	/**
	 * Constructs an instance with the specified message.
	 */
	public SessionExpiredException(String msg) {
		super(DEFAULT_MESSAGE + " ( " + msg + " )");
	}

	/**
	 * Constructs an instance with the specified message and cause.
	 */
	public SessionExpiredException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
