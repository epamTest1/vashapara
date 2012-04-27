package com.couple.services.external;

import java.util.List;
import java.util.Map;

class ResponseWrapper<T> {
	public List<T> response;
	public Map<String, Object> error;
	
	public List<T> getResponse() {
		return response;
	}
	
	public void setResponse(List<T> response) {
		this.response = response;
	}
	
	public Map<String, Object> getError() {
		return error;
	}
	
	public void setError(Map<String, Object> error) {
		this.error = error;
	}
}
