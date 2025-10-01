package com.propadda.prop.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import com.propadda.prop.model.Users;

@Service
public class JwtService {

    private static final String SECRET_KEY = "my-super-secret-key-for-jwt-signing-123456"; // ideally from env
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 24 hours

    private Key getSignKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(Users user) {
        return Jwts.builder()
            .setSubject(user.getEmail())
            .claim("role", user.getRole().name())
            .claim("userId", user.getUserId())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
            .signWith(getSignKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, Users user) {
        final String username = extractUsername(token);
        return username.equals(user.getEmail()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSignKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }
}
