package com.testbaron.rest.model;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class MessageResponse {
	private String code;
	private HttpStatus status;
	private String message;
	private Object result;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
}
