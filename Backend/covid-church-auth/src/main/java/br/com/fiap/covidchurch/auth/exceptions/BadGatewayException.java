package br.com.fiap.covidchurch.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class BadGatewayException extends Exception{

	private static final long serialVersionUID = -3442328695358391317L;

	public BadGatewayException(String message) {
		super(message);
	}
	
}