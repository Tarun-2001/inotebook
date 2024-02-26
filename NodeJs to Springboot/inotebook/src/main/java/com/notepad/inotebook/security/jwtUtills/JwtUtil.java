package com.notepad.inotebook.security.jwtUtills;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {

    private static final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static String ID;
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
        return ID=claims.getSubject();

    }

    public static String generateToken(String email){

        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime()+3600000);
        return  Jwts.builder()
                .setSubject(email)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compact();
    }
}
