package api.gateway.security.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
         log.error("Token invalido, error: {}", e.getMessage());
         return false;
      }
   }

   public String extractUsername(String token) {
      Claims claims = this.extractClaims(token);
      return claims.getSubject();
   }

   public Claims extractClaims(String token) {
      return Jwts.parser()
            // .setSigningKey(this.getSigningKey())
            .setSigningKey(this.secret)
            .parseClaimsJws(token)
            .getBody();

   }

   private SecretKey getSigningKey() {

      // * 1.
      byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
      return Keys.hmacShaKeyFor(keyBytes);

      // * 2.
      /*
       * byte[] keyBytes = this.secret.getBytes(StandardCharsets.UTF_8);
       * return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS512.getJcaName());
       */

      // * 3.

      /*
       * try {
       * MessageDigest digest = MessageDigest.getInstance("SHA-512");
       * byte[] hashedKey =
       * digest.digest(this.secret.getBytes(StandardCharsets.UTF_8));
       * return new SecretKeySpec(hashedKey, SignatureAlgorithm.HS512.getJcaName());
       * } catch (Exception e) {
       * throw new RuntimeException("Error generando clave", e);
       * }
       */

      /*
       * try {
       * MessageDigest digest = MessageDigest.getInstance("SHA-512");
       * byte[] keyBytes =
       * digest.digest(this.secret.getBytes(StandardCharsets.UTF_8));
       * return new SecretKeySpec(keyBytes, "HmacSHA512");
       * } catch (Exception e) {
       * throw new RuntimeException("Error al generar la clave", e);
       * }
       */

   }

}
