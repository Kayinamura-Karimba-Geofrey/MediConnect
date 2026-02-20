package com.example.health_platform.auth.security;

import com.example.health_platform.auth.model.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String ACCESS_SECRET_STRING;

    @Value("${application.security.jwt.refresh-token.secret-key}")
    private String REFRESH_SECRET_STRING;


    private Key getAccessKey() {
        return Keys.hmacShaKeyFor(ACCESS_SECRET_STRING.getBytes());
    }

    private Key getRefreshKey() {
        return Keys.hmacShaKeyFor(REFRESH_SECRET_STRING.getBytes());
    }

    

    
    public String generateAccessToken(String userId, Role role) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("role", role.name())
                .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) // 15 mins
                .signWith(getAccessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    
    public String generateRefreshToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + 30L * 24 * 60 * 60 * 1000)) // 30 days
                .signWith(getRefreshKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    

    public boolean validateAccessToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getAccessKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getRefreshKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    

    public String extractUserIdFromAccessToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getAccessKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String extractUserIdFromRefreshToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getRefreshKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public String extractRoleFromAccessToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getAccessKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.get("role", String.class);
    }
}
