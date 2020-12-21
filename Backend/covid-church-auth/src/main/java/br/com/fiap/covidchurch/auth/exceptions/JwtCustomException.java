package br.com.fiap.covidchurch.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class JwtCustomException extends Exception{

	private static final long serialVersionUID = 5115116319077873790L;

	public JwtCustomException(String message) {
		super(message);
	}
	
}
