package br.com.fiap.covidchurch.auth.security;

import java.nio.charset.Charset;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import br.com.fiap.covidchurch.auth.dtos.JwtRequest;
import br.com.fiap.covidchurch.auth.exceptions.JwtCustomException;

public class RequestTools {


	public static JwtRequest getBasicInfo(HttpServletRequest request) throws JwtCustomException {
		String authorization = request.getHeader("Authorization");
		if (authorization != null && authorization.startsWith("Basic")) {
			JwtRequest basicInfo = new JwtRequest();
			// Authorization: Basic base64credentials
			String base64Credentials = authorization.substring("Basic".length()).trim();
			String credentials = new String(Base64.getDecoder().decode(base64Credentials),
					Charset.forName("UTF-8"));
			// credentials = username:password
			String[] values = credentials.split(":",2);
			basicInfo.setUsername(values[0]);
			basicInfo.setPassword(values[1]);
			return basicInfo;
		}
		else {
			throw new JwtCustomException("It is not a basic authentication");
		}
	}


	public static String getBearerInfo(HttpServletRequest request) throws JwtCustomException {

		final String requestTokenHeader = request.getHeader("Authorization");

		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			return requestTokenHeader.substring(7).trim();

		} else {
			throw new JwtCustomException("JWT Token does not begin with Bearer String");
		}

	}	

	
	public static String getHeaderInfo(HttpServletRequest request, String propertie) {
		return request.getHeader(propertie);
	}

	
	

}
