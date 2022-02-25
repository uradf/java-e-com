package com.example.ecommercefinal.config;

import com.example.ecommercefinal.entity.Customer;
import com.example.ecommercefinal.exception.AuthenFailedException;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;

import java.util.Date;
import static java.lang.String.format;


@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

    private String jwtSecret = "zdtlD3JK56m6wTTgsNFhqzjqP";
    private String jwtIssuer = "example.io";


    public String generateAccessToken(Customer user) {
        return Jwts.builder()
                .setSubject(format("%s,%s", user.getId(), user.getUserid()))
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public int getUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject().split(",")[0]);
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject().split(",")[1];
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getExpiration();
    }

    public boolean validate(String token) {
        boolean result = false;
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            result = true;
        } catch (Exception ex) {
            // Implement Something
        }
        finally {
            return result;
        }
    }


}
