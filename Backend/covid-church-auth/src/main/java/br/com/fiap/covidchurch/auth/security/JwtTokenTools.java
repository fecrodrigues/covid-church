package br.com.fiap.covidchurch.auth.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.servlet.http.HttpServletRequest;

import br.com.fiap.covidchurch.auth.exceptions.JwtCustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



public class JwtTokenTools {


	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	private static String secret = "Eng@$007";



	public static boolean isChangeOnlyFromToken(String token) {
		return Boolean.parseBoolean(getClaimFromToken(token, "isChangeOnly"));
	}


	public static String getClaimFromToken(HttpServletRequest request, String claimName) {

		String jwtToken = null;
		try {
			jwtToken = RequestTools.getBearerInfo(request);
		} catch (JwtCustomException e) {}

		return getStatusFromToken(jwtToken, claimName);
	}
	public static String getClaimFromToken(String token, String claimName) {
		return getStatusFromToken(token, claimName);
	}

	public static String getStatusFromToken(String token, String claimName) {
		final Claims claims = getAllClaimsFromToken(token);
		return claims.get(claimName).toString();
	}

	public static <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}


	private static Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}


	public static String generateToken(String userId, String status, boolean isChangeOnly) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("status", status);
		claims.put("isChangeOnly", isChangeOnly);
		return doGenerateToken(claims, userId);
	}

	private static String doGenerateToken(Map<String, Object> claims, String subject) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	
	public static Boolean validateToken(String token) {

		try {

			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

			if(JwtTokenTools.isChangeOnlyFromToken(token)) {
				return false;
			}

			return true;
		} catch (SignatureException e) {
			//logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			//logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			//logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			//logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			//logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
		
	}
	public static Boolean validateToken(HttpServletRequest request) {

		String jwtToken = null;
		try {
			jwtToken = RequestTools.getBearerInfo(request);
		} catch (JwtCustomException e) {}

		return JwtTokenTools.validateToken(jwtToken);
	
	}
	
	public static Boolean validateTokenReset(String token) {

		try {

			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);

			if(!JwtTokenTools.isChangeOnlyFromToken(token)) {
				return false;
			}

			return true;
		} catch (SignatureException e) {
			//logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			//logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			//logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			//logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			//logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;


		//return (!isTokenExpired(token));  
	}
	public static Boolean validateTokenReset(HttpServletRequest request) {

		String jwtToken = null;
		try {
			jwtToken = RequestTools.getBearerInfo(request);
		} catch (JwtCustomException e) {}

		return validateTokenReset(jwtToken);
	}

	public static String getTokenFromRequest(HttpServletRequest request) {
		String jwtToken = null;
		try {
			jwtToken = RequestTools.getBearerInfo(request);
		} catch (JwtCustomException e) {}

		return jwtToken;
	}

		
	
	


}