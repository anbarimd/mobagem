package net.h2.web.core.base.exception.handler;

import java.util.UUID;

import net.h2.web.core.base.exception.BaseServerBusinessException;
import net.h2.web.core.base.exception.ErrorResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * @author Mohammad Anbari
 *
 */
//@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{
	
	Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);
	
	
	@ExceptionHandler(value = BaseServerBusinessException.class)
	protected ResponseEntity<Object> handleBaseServerBusinessException(BaseServerBusinessException ex, WebRequest request)
	{
		logger.error(ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorReferenceId(UUID.randomUUID().toString());
		errorResponse.setType(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}
	
	@ExceptionHandler(value = { Exception.class})
	protected ResponseEntity<Object> handleResourceNotFoundException(Exception ex,
			WebRequest request) {
		logger.error(ex.getMessage(), ex);
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setErrorReferenceId(UUID.randomUUID().toString());
		errorResponse.setType(HttpStatus.INTERNAL_SERVER_ERROR.name());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

}
