package api.gateway.security.services;

import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class JwtService {

   @Value("${jwt.secret}")
   private String secret;

   public boolean validateToken(String token) {
      try {
         this.extractClaims(token);
         return true;
      } catch (ExpiredJwtException e) {
         log.error("Token expirado: {}", e.getMessage());
         return false;
      } catch (JwtException e) {
         log.error("!Token invalido¡");
         return false;
      }
   }

   public String extractUsername(String token) {
      Claims claims = this.extractClaims(token);
      return claims.getSubject();
   }

   public Claims extractClaims(String token) {
      return Jwts.parser()
            .verifyWith(this.getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
   }

   private SecretKey getSigningKey() {
      byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
      return Keys.hmacShaKeyFor(keyBytes);
   }

}
