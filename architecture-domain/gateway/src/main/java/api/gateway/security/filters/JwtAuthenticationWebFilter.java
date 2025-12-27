package api.gateway.security.filters;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import api.gateway.security.services.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Mono;

@Component
@Log4j2
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "app.security", name = "enabled", havingValue = "true", matchIfMissing = true)
public class JwtAuthenticationWebFilter implements WebFilter {

   private final JwtService jwtService;

   @Value("${app.security.allowed-origins}")
   private String allowedOrigin;

   @Value("${spring.profiles.active}")
   private String profile;

   @Override
   public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

      String token = this.extractToken(exchange);

      // * 1. Validación de Issuer
      if (!this.isExpectedIssuer(exchange)) {
         log.error("Issuer no permitido: {}", this.extractIssuer(exchange));
         return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));
      }

      // * 2. Validación de Token
      if (token != null && this.jwtService.validateToken(token)) {

         String username = this.jwtService.extractUsername(token);
         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
               username,
               null,
               Collections.emptyList());

         log.info("Bienvenido: {}", username);
         return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));

      }

      // * 3. Token no válido
      return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED));

   }

   private String extractToken(ServerWebExchange exchange) {
      String tokenBearer = exchange.getRequest().getHeaders().getFirst("Authorization");
      if (tokenBearer != null && tokenBearer.startsWith("Bearer ")) {
         return tokenBearer.substring(7);
      }
      return null;
   }

   private boolean isExpectedIssuer(ServerWebExchange exchange) {
      String reqOrigin = this.extractIssuer(exchange);
      return this.profile.equals("dev") // ? 1. Desarrollo
            || reqOrigin == null // ! 2. Browser o Postman(Comentar en producción)
            || reqOrigin.equalsIgnoreCase(allowedOrigin); // ? 3. Aplicación api fetch
   }

   private String extractIssuer(ServerWebExchange exchange) {
      return exchange.getRequest().getHeaders().getOrigin();
   }

}
