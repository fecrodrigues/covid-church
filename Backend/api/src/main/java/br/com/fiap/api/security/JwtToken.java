package br.com.fiap.api.security;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtToken {

	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expire}")
	private int expire;
	
    public String generateToken(String username, String cpf){
        Map<String, Object> claims = new HashMap<>();
        claims.put("cpf", cpf);
        Date dataCriacao = getFromLocalDateTime(LocalDateTime.now());
        Date dataExpiracao = getFromLocalDateTime(LocalDateTime.now().plusMinutes(expire));
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(dataCriacao)
                .setExpiration(dataExpiracao)
                .setSubject(username)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    
    public String getUsernameFromToken(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token.replace("Bearer ", ""))
                .getBody();
        return claims.getSubject();
    }

    private Date getFromLocalDateTime(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(OffsetDateTime.now().getOffset()));
    }
}
