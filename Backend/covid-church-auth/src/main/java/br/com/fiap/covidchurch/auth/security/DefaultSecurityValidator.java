package br.com.fiap.covidchurch.auth.security;

import javax.servlet.http.HttpServletRequest;

public class DefaultSecurityValidator implements ISecurity {

	@Override
	public ValidateResponse validate(HttpServletRequest request) {
		
		ValidateResponse response = new ValidateResponse();
		response.setToken(JwtTokenTools.getTokenFromRequest(request));

		boolean validated = JwtTokenTools.validateToken(response.getToken()) &&
				!Boolean.parseBoolean(JwtTokenTools.getClaimFromToken(request, "isChangeOnly"));
		
		response.setValidated(validated);
		
		return response;
	
	}
	
	
}
