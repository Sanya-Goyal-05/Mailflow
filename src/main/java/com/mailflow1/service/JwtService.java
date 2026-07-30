package com.mailflow1.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    // Secret key (we'll move this to application.properties later)
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123456";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Generate JWT Token
    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Email
    public String extractEmail(String token) {

        return extractClaims(token).getSubject();
    }

    // Validate Token
    public boolean isTokenValid(String token, String email) {

        return extractEmail(token).equals(email)
                && !extractClaims(token).getExpiration().before(new Date());
    }

    // Extract Claims
    private Claims extractClaims(String token) {

        return Jwts.parser()
                .verifyWith((javax.crypto.SecretKey) key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}