package com.lingotutor.apigateway.util;

import java.security.Key;


import org.springframework.stereotype.Component;

import java.util.function.Function;

import io.jsonwebtoken.Claims;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

	/*Same secret as Auth Service*/
    public static final String SECRET = "E04693C65EFB3E1D475B32FBB07A310316011017D16925A8C5479C76C8C2291E";
	public static final String SEPARATOR =";";

    public void validateToken(final String token) {
        Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
    }


    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }


	public String[] extractUserIdAndUserName(String token) {
		return extractClaim(token, Claims::getSubject).split(SEPARATOR);
	}
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { 
		final Claims claims = extractAllClaims(token); 
		return claimsResolver.apply(claims); 
	} 

	private Claims extractAllClaims(String token) { 
		return Jwts 
				.parserBuilder() 
				.setSigningKey(getSignKey()) 
				.build() 
				.parseClaimsJws(token) 
				.getBody(); 
	} 

}
