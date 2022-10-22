package com.henz.advice;

import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.henz.custom_exception.EmployeeNotFoundException;
import com.henz.custom_exception.EmptyInputException;

@ControllerAdvice //handle exceptions globally instead of handling in controller directly
public class MyControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EmptyInputException.class) //handle custom exception, thrown in addEmployee of Service
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		return new ResponseEntity<String>("Input field is empty: "+emptyInputException.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoSuchElementException.class) //handle known exception, thrown in getEmployeeById of Service
	public ResponseEntity<String> handleNoSuchElement(NoSuchElementException noSuchElementException){
		return new ResponseEntity<String>("No value present in DB: "+noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class) //handle custom exception, thrown in deleteEmployeeById of Service
	public ResponseEntity<String> handleNoEmployeeFound(EmployeeNotFoundException employeeNotFoundException){
		return new ResponseEntity<String>("Employee not found: "+employeeNotFoundException.getErrorMessage(), HttpStatus.NOT_FOUND);
	}
	
	@Override //override method of ResponseEntityExceptionHandler for handling exceptions of METHOD_NOT_ALLOWED
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Object>("Method not allowed here dude!", status);
	}
}
