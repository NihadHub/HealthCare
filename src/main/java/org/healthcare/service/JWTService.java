package org.healthcare.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;


@Service
public class JWTService {
    private final String secret;
    private final long experation;
      public JWTService(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long experation) {
          this.secret = secret;
          this.experation = experation;
      }

    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + experation))
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token){
          return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
          return extractClaim(token,Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
          Claims claims= parseClaims(token);
          return claimsResolver.apply(claims);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
          if(token==null) return false;
          final String username= extractUsername(token);
          return username.equals(userDetails.getUsername())&& !isTokenExpired(token);

    }

    private boolean isTokenExpired(String token){
          try {
              return extractExpiration(token).before(new Date());
          }catch (JwtException e){
              return true;
          }
    }
}
