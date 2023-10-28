package com.proyectofinalg30.empresass.empresas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private TokenUtils() {
        throw new IllegalStateException("Utility class");
    }
    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 1_800_000; // 1_800 segundos = 30 minutos

    public static String createToken(Long id, String miAccessTokenSecret) {
        Integer expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("type", "access");
        extra.put("fresh", false);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setSubject(String.valueOf(id))
                .setNotBefore(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(SignatureAlgorithm.HS256, miAccessTokenSecret.getBytes()) //.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
}
