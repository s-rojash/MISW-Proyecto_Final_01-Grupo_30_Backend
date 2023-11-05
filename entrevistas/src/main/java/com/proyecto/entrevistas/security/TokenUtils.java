package com.proyecto.entrevistas.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtils {

    private static final String AUTHORIZATION = "Authorization";

    private TokenUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String decryptToken(HttpServletRequest request, String miAccessTokenSecret) {
        String bearerToken = request.getHeader(AUTHORIZATION);
        if (bearerToken.startsWith("Bearer ")) {
            try {
                bearerToken = bearerToken.replace("Bearer ", "");
                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(miAccessTokenSecret.getBytes())
                        .parseClaimsJws(bearerToken);
                Claims body = claimsJws.getBody();
                System.out.println("IdEmpresa: "+body.get("idEmpresa"));
                return (String) body.get("idEmpresa");
            } catch (Exception e) {
                return null;
            }
        }
        else {
            return null;
        }
    }
}