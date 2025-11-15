package com.example.health_platform.auth.security;

import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

@Service
public class JwtService {

    private final String ACCESS_SECRET = "ACCESS_SECRET_KEY_123";
    private final String REFRESH_SECRET = "REFRESH_SECRET_KEY_456";

    // Generate Access Token (15 minutes)
    public String generateAccessToken(String userId, String role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role)
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, ACCESS_SECRET)
                .compact();
    }

    // Generate Refresh Token (30 days)
    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, REFRESH_SECRET)
                .compact();
    }

    // Validate Access Token
    public boolean validateAccessToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(ACCESS_SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Validate Refresh Token
    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(REFRESH_SECRET)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Extract userId from Refresh Token
    public String extractUserIdFromRefreshToken(String token) {
        return Jwts.parser()
                .setSigningKey(REFRESH_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Extract userId from Access Token
    public String extractUserIdFromAccessToken(String token) {
        return Jwts.parser()
                .setSigningKey(ACCESS_SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
