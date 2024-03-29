package com.tsubaki.security.jwt;

import com.tsubaki.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${security.app.jwtSecret}")
    private String jwtSecret;

    @Value("${security.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Value("${security.app.jwtExpitationLimitHours}")
    private int jwtExpirationLimitH;

    public String generateJwtToken(String username) {
//        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String getUserNameFromJwtTokenn(String token) {
        try {
            UserDetailsImpl user = (UserDetailsImpl) Jwts
                    .parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parse(token)
                    .getBody();
            return user.getUsername();
        } catch (ExpiredJwtException e) {
            return e.getClaims().getSubject();
        }
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token in unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

    public boolean expiredInLimitTime(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            // not expired
            return false;
        } catch (ExpiredJwtException e) {
            Date expDate = e.getClaims().getExpiration();
            Date limitedExpDate = Date.from(expDate.toInstant().plus(Duration.ofHours(jwtExpirationLimitH)));
            return expDate.before(new Date()) && limitedExpDate.after(new Date());
        }
    }
}
