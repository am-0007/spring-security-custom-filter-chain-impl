package com.springsecuirty2023.config.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
@Data
public class JwtTokenService {


    private String secretKey = "sdjkfhjas1283./asd-129234][sa";

    private int expiresIn = 1000;

    private UserDetails userDetails;

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }


    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }
    public String generateToken(String username, Collection<? extends GrantedAuthority> authorities) throws InvalidKeySpecException, NoSuchAlgorithmException {

        List<String> authority = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                /*.setIssuer( appName )*/
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("Roles", authority)
                .signWith(SIGNATURE_ALGORITHM, secretKey )
                .compact();
    }

    public boolean isTokenExpired(String token) {
        Date expireDate=getExpirationDate(token);
        return expireDate.before(new Date());
    }


    private Date getExpirationDate(String token) {
        Date expireDate;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            expireDate = claims.getExpiration();
        } catch (Exception e) {
            expireDate = null;
        }
        return expireDate;
    }


    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getToken(HttpServletRequest request) {

        String authHeader = getAuthHeaderFromHeader(request);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring("Bearer ".length());
        }

        return null;
    }

    public String getAuthHeaderFromHeader(HttpServletRequest request ) {
        return request.getHeader("Authorization");
    }

    private Date generateExpirationDate() {
        return new Date(new Date().getTime() + expiresIn * 1000L);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username != null &&
                        username.equals(userDetails.getUsername()) &&
                        !isTokenExpired(token)
        );
    }

}
