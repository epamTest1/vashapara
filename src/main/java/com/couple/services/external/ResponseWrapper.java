package com.couple.services.external;

import java.util.List;

class ResponseWrapper<T> {
	public List<T> response;
	
	public List<T> getResponse() {
		return response;
	}
	
	public void setResponse(List<T> response) {
		this.response = response;
	}
}
