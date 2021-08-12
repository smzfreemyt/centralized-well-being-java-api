package com.cewb.app.security.jwt;

import java.util.Date;

import com.cewb.app.security.service.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    private final String jwtSecret = "cewbsSecretKey$";

    private final int jwtExpiration = 120000;

    public String generateJwtToken(Authentication authentication) {

        UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
		                .setSubject((userPrincipal.getEmail()))
		                .setIssuedAt(new Date())
		                .setExpiration(new Date((new Date()).getTime() + jwtExpiration * 1000))
		                .signWith(SignatureAlgorithm.HS512, jwtSecret)
		                .compact();
    }
    
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            logger.info("THIS IS A VALID TOKEN.");
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature : " + e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token : " + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is Expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Claims is empty: " + e.getMessage());
        }
        
        return false;
    }

    /**
     * Test token in https://jwt.io/#encoded-jwt if sub exists
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}