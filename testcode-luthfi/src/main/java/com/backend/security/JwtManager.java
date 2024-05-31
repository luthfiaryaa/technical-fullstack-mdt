package com.backend.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtManager {

    private final String SECRET_KEY = "liberate-tutume-ex-inferis-ad-astra-per-aspera";
    private final long TOKEN_EXPIRATION_MS = 3600000; // 1 hour in milidetik

    public String generateToken(String username, String secretKey, String subject, String audience) {
        long currentTimeMillis = System.currentTimeMillis();
        Date now = new Date(currentTimeMillis);

        Date expirationDate = new Date(currentTimeMillis + TOKEN_EXPIRATION_MS);


        JwtBuilder builder = Jwts.builder();
        builder.setSubject(subject)
                .setIssuer("http://localhost:8080")
                .setAudience(audience)
                .claim("username", username)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secretKey);
        return builder.compact();
    }

    private Claims getClaimsFromToken(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);
        Jws<Claims> signatureAndClaims = parser.parseClaimsJws(token);
        Claims claims = signatureAndClaims.getBody();
        return claims;
    }

    public String getUsernameByToken(String token){
        Claims claims = getClaimsFromToken(token);
        String username = claims.get("username", String.class);
        return username;
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameByToken(token);
        Boolean isMatch = username.equals(userDetails.getUsername());
        return isMatch;
    }
}
