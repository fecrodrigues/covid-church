package br.com.fiap.covidchurch.auth.security;

import javax.servlet.http.HttpServletRequest;

public interface ISecurity {
	
	ValidateResponse validate(HttpServletRequest request);

}
