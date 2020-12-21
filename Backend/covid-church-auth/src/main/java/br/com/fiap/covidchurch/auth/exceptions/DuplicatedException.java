package br.com.fiap.covidchurch.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedException extends Exception{

	private static final long serialVersionUID = -1904589210423311918L;

	public DuplicatedException(String message) {
		super(message);
	}
	
}
