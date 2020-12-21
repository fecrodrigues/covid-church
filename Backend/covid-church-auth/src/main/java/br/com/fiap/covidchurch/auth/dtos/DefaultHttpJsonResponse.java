package br.com.fiap.covidchurch.auth.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.SimpleTimeZone;

public class DefaultHttpJsonResponse<T> {
	
	private String timestamp;
	private int errorCode;
	private String errorMessage;
	private T data;
	
	
	public DefaultHttpJsonResponse(){};
	public DefaultHttpJsonResponse(
			int errorCode,
			String errorMessage,
			T data
			){
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.data = data;
		this.timestamp = setCurrentTime();
		
	};
	public DefaultHttpJsonResponse(T data){
		this.errorCode = 0;
		this.errorMessage = null;
		this.data = data;
		this.timestamp = setCurrentTime();
		
	};
	
	private String setCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat();
		sdf.setTimeZone(new SimpleTimeZone(0, "GMT"));
		sdf.applyPattern("dd/MMM/yyyy HH:mm:ss z");
		return sdf.format(new Date());
	}
	
	
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}

		

}
