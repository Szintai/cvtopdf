package com.cvtopdf.config;


import com.cvtopdf.exception.NoResourceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Global Exception handler over rest endpoints
 * <p>
 * If an exception occurs during a rest call, it will translate to a specific HTTP RESPONSE
 */
@ControllerAdvice
public class GlobalRestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { NoResourceException.class })
	protected String handleNoResourceException(RuntimeException ex, WebRequest request) {
		//TODO
		return "";
	}
}
