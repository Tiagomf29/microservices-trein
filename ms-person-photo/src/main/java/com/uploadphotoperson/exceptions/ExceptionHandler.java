package com.uploadphotoperson.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{

	@Getter @Setter
	@JsonInclude(Include.NON_NULL)
	public class Problem{		
		private String title;
		private int status;
		private String field;
		private String detail;			
		List<Errors> listErros;
	}
	
	@Getter @Setter
	public class Errors{
		private String field;
		private String detail;	
	}
	
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Problem p = new Problem();
		p.setTitle("Erro ao processar a requisição.");
		p.setStatus(status.value());
		p.setDetail(ex.getBody().getDetail());
		
		List<Errors> listError = new ArrayList<>();
		
		for(ObjectError oe : ex.getBindingResult().getAllErrors()) {			
		
			Errors erros = new Errors();
			erros.setField(((FieldError)oe).getField());
			erros.setDetail(oe.getDefaultMessage());
			listError.add(erros);
		}
		p.setListErros(listError);
		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Problem p = new Problem();
		p.setTitle("Erro ao processar a requisição.");
		p.setStatus(status.value());
		p.setDetail(ex.getMessage());
		
		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		Problem p = new Problem();
		p.setTitle("Erro ao processar a requisição.");
		p.setStatus(status.value());
		p.setDetail(ex.getMessage());
		
		return handleExceptionInternal(ex, p, headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		Problem p = new Problem();
		p.setTitle("Erro ao processar a requisição.");
		p.setStatus(statusCode.value());
		p.setDetail(ex.getMessage());
		
		return super.handleExceptionInternal(ex, p, headers, statusCode, request);
	}
	
}
