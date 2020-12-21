package br.com.fiap.covidchurch.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception{

	private static final long serialVersionUID = 6367660221490877908L;

	public UnauthorizedException(String message) {
		super(message);
	}
	
}