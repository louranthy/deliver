package com.platform.delivery.error;

import org.springframework.http.HttpStatus;

public class ApiError extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6748138410697159853L;
	private HttpStatus status;
    private String error;
    
    public ApiError(HttpStatus status, String error) {
        super();
        this.error = error;
        this.status = status;
    }
    
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
