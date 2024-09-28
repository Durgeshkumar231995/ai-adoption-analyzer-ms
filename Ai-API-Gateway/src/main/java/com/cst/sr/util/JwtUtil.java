package com.cst.sr.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    public Claims getAllClaimsFromToken(String token) {
        if(token!=null && !(token.startsWith("Bearer"))){
            throw new RuntimeException("JWT Token is missing");
        }
        String jwtToken = token.substring(7);
        return (Claims)Jwts.parser().setSigningKey(secret).parse(jwtToken).getBody();
    }


}

