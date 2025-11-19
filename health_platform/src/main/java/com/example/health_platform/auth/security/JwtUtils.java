package com.example.health_platform.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtils {

    private final String JWT_SECRET = "MySuperSecretKeyForJWT123!MySuperSecretKeyForJWT123!";
    private final long JWT_EXPIRATION_MS = 24 * 60 * 60 * 1000; // 1 day

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(JWT_SECRET.getBytes());
    }

    
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, getSigningKey().getEncoded())
                .compact();
    }

    
    public String getEmailFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(getSigningKey().getEncoded())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
