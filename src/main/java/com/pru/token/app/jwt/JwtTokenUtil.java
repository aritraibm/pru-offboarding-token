package com.pru.token.app.jwt;

import com.pru.token.app.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
	
	private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour
	
	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	public String generateAccessToken(User user) {
		
		return Jwts.builder()
				.setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
				.setIssuer("Onboarding Team IBM Prudential")
				.claim("roles", user.getRole().toString())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)
				.compact();
	}
	
//	public boolean validateAccessToken(String token) {
//		try {
//			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
//			return true;
//		} catch (ExpiredJwtException ex) {
//			LOGGER.error("JWT expired", ex.getMessage());
//		} catch (IllegalArgumentException ex) {
//			LOGGER.error("Token is null, empty or only whitespace", ex.getMessage());
//		} catch (MalformedJwtException ex) {
//			LOGGER.error("JWT is invalid", ex);
//		} catch (UnsupportedJwtException ex) {
//			LOGGER.error("JWT is not supported", ex);
//		} catch (SignatureException ex) {
//			LOGGER.error("Signature validation failed");
//		}
//		
//		return false;
//	}
//	
//	public String getSubject(String token) {
//		return parseClaims(token).getSubject();
//	}
//	
//	public Claims parseClaims(String token) {
//		return Jwts.parser()
//				.setSigningKey(SECRET_KEY)
//				.parseClaimsJws(token)
//				.getBody();
//	}
	public Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
