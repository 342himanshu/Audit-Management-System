package com.example.authentication.service;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;

@Service
public class JwtUtil {

	private String secretKey="${jwt.secret}";
	
	public String extractUsername(String token){
		return extractClaims(token,Claims::getSubject);
	}
	public Claims extractAllClaims(String token) {
		String token1 = token.trim().replaceAll("\\0xfffd", "");
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token1).getBody();
	}
	public <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
		Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
}
