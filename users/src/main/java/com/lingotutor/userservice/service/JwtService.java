package com.lingotutor.userservice.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {

	/* Use below same secret to validate token in API gateway or service */
	public static final String SECRET = "E04693C65EFB3E1D475B32FBB07A310316011017D16925A8C5479C76C8C2291E";
	public static final String SEPARATOR =";";

	public String generateToken(Long userId, String username) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userId.toString(), username);
	}

	private String createToken(Map<String, Object> claims, String userId, String username) {
		StringBuffer sb = new StringBuffer().append(userId).append(SEPARATOR).append(username);
		return Jwts.builder().setClaims(claims).setSubject(sb.toString())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String[] extractUserIdAndUserName(String token) {
		return extractClaim(token, Claims::getSubject).split(SEPARATOR);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
	}

	/*
	 * uncomment if needed private Boolean isTokenExpired(String token) { return
	 * extractExpiration(token).before(new Date()); }
	 */

	public void validateToken(final String token) {
		Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
	}

}
