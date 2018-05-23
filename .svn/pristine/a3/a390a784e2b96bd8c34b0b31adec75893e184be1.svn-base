package net.h2.web.core.base.exception;


/**
 * @author Mohammad Anbari
 *
 */
public class BaseServerBusinessException extends Exception{

	private static final long serialVersionUID = 1L;
	private static final String DELIMETER = ":";
	private String exceptionCode;	
	
	public BaseServerBusinessException() {
	}
	
	public BaseServerBusinessException(String exceptionCode,Throwable e) {
		super(exceptionCode,e);
		this.exceptionCode = exceptionCode;
	}
	
	public BaseServerBusinessException(String exceptionCode,String exceptionMsg,Throwable e) {
		super(exceptionCode + DELIMETER + exceptionMsg,e);
		this.exceptionCode = exceptionCode;
	}
	
	public String getExceptionCode() {
		return exceptionCode;
	}

	
	

}
