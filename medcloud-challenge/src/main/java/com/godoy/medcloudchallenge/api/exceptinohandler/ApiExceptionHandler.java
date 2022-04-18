package com.godoy.medcloudchallenge.api.exceptinohandler;

import java.time.OffsetDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.godoy.medcloudchallenge.domain.exception.ConflictException;
import com.godoy.medcloudchallenge.domain.exception.DealException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DealException.class)
	public ResponseEntity<Object> handleDealException(DealException ex, WebRequest request) {

		var status = HttpStatus.NOT_FOUND;

		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle(ex.getMessage());

		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConflictException.class)
	public ResponseEntity<Object> handleConflictException(ConflictException ex, WebRequest request) {

		var status = HttpStatus.CONFLICT;

		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle(ex.getMessage());

		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {

		var status = HttpStatus.BAD_REQUEST;
		List<String> constraintList = ex.getConstraintViolations().stream().map(x -> x.getPropertyPath().toString()).toList();

		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle("The following fields are invalid: " + constraintList.toString());

		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}
	
	public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		var exceptionHandlerObj = new ExceptionHandlerObj();
		exceptionHandlerObj.setStatus(status.value());
		exceptionHandlerObj.setDateTime(OffsetDateTime.now());
		exceptionHandlerObj.setTitle("Invalid date format. Inform the birth date on the following format: yyyy-mm-dd.");

		return handleExceptionInternal(ex, exceptionHandlerObj, new HttpHeaders(), status, request);
	}

}
