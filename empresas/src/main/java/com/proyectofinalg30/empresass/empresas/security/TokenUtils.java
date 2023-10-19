package com.proyectofinalg30.empresass.empresas.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private static final String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";
    private static final Integer ACCESS_TOKEN_VALIDITY_SECONDS = 1_800; // 1_800 segundos = 30 minutos

    public static String createToken(Long id) {
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
                .signWith(SignatureAlgorithm.HS256, ACCESS_TOKEN_SECRET.getBytes()) //.signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
}
