package com.notepad.inotebook.security.jwtUtills;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;

@Slf4j
public class JwtUtil {

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static boolean validateToken(String jwtToken) {
        return parseToken(jwtToken)!=null;
    }

    private static Claims parseToken(String jwtToken){
        JwtParser build = Jwts.parser().setSigningKey(secretKey).build();
        try {
            return  build.parseSignedClaims(jwtToken).getPayload();
        } catch (JwtException |IllegalArgumentException e) {
            log.error("JWT Exception occurred");
        }
        return null;
    }

    public static String getUserNameFromToken(String jwtToken) {
        Claims claims = parseToken(jwtToken);
        return claims.getSubject();

    }
}
