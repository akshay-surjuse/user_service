package com.humancloud.task_management_tool.api_gateway.utils;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	
	 public static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
	 
	 
	 public String extractUsername(String token) {
	        return extractClaim(token, Claims::getSubject);
	 }

	 public List extractRoles(String token) {
	        Claims allClaims = extractAllClaims(token);
	        List list = allClaims.get("roles", List.class);
	        return list;
	 }
	 
	 public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
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
	 

    public void validateToken(final String token) {
    	System.out.println("api gateway token " + token);
        Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token);
        System.out.println("claimsJws: " + claimsJws);
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
