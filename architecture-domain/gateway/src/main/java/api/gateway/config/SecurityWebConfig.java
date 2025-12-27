package api.gateway.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import api.gateway.security.filters.JwtAuthenticationWebFilter;
import lombok.AllArgsConstructor;

@Configuration
@EnableWebFluxSecurity
@AllArgsConstructor
public class SecurityWebConfig {

   private final ObjectProvider<JwtAuthenticationWebFilter> jwtAuthenticationFilter;

   @Bean
   @ConditionalOnProperty(prefix = "app.security", name = "enabled", havingValue = "true", matchIfMissing = true)
   SecurityWebFilterChain securityWebFilterChainEnable(ServerHttpSecurity http) throws Exception {
      http
            .csrf(csrf -> csrf.disable()) // ! Permite token en headers
            .authorizeExchange(authz -> authz.anyExchange().authenticated())
            .addFilterAt(this.jwtAuthenticationFilter.getIfAvailable(), SecurityWebFiltersOrder.AUTHENTICATION);

      return http.build();
   }

   @Bean
   @ConditionalOnProperty(prefix = "app.security", name = "enabled", havingValue = "false")
   SecurityWebFilterChain securityWebFilterChainDisable(ServerHttpSecurity http) throws Exception {
      http
            .csrf(csrf -> csrf.disable()) // ! Permite token en headers
            .authorizeExchange(authz -> authz.anyExchange().permitAll());

      return http.build();
   }

}
