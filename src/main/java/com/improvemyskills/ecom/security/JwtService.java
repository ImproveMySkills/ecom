package com.improvemyskills.ecom.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {

    // Utilisez une clef forte (32+ bytes), lisez-la depuis les variables d'environnement:
/*
    private final Key key = Keys.hmacShaKeyFor(
            System.getenv("JWT_SECRET_CODE").getBytes(StandardCharsets.UTF_8));
*/
    private static final String JWT_SECRET_CODE =
            "mySuperUltraSecretKeyForJWT_HS256_ChangeMe123456789!";

    private final Key key = Keys.hmacShaKeyFor(
            JWT_SECRET_CODE.getBytes(StandardCharsets.UTF_8));

    private static final long ACCESS_TTL_MS  = 15 * 60 * 1000;   // 15 minutes
    private static final long REFRESH_TTL_MS = 7  * 24 * 60 * 60 * 1000; // 7 jours

    public String generateAccessToken(String username, Collection<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TTL_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String username) {
        // jti: identifiant de token pour rotation/blacklist
        String jti = UUID.randomUUID().toString();
        return Jwts.builder()
                .setSubject(username)
                .setId(jti)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TTL_MS))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parse(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
    }

    public String getUsername(String token) { return parse(token).getBody().getSubject(); }

    public boolean isExpired(String token) {
        return parse(token).getBody().getExpiration().before(new Date());
    }
}
