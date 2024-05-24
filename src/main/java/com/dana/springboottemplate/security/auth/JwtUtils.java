package com.dana.springboottemplate.security.auth;

import com.dana.springboottemplate.model.User;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${app.jwt.secret}")
	private String jwtSecret;

	@Value("${app.jwt.expiration-ms}")
	private int jwtExpirationMs;

	public String generateJwtToken(User userPrincipal) {

		return Jwts.builder()
			.setSubject((userPrincipal.getId()))
			.claim("email", userPrincipal.getEmail())
			.claim("phoneNumber", userPrincipal.getPhoneNumber())
			.claim("id", userPrincipal.getId())
			.claim("authorities", List.of("user"))
			.setIssuedAt(new Date())
			.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	public String getSubjectFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public Claims getClaimsFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		}
		catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		}
		catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		}
		catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		}
		catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		}
		catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}

}
