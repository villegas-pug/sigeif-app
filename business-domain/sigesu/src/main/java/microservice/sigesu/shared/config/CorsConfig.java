package microservice.sigesu.shared.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas de la aplicación
                .allowedOriginPatterns("*") // Dominios permitidos
                .allowedMethods("*") // Métodos HTTP permitidos
                .allowedHeaders("*") // Headers permitidos en las solicitudes
                .exposedHeaders("Authorization", "Content-Type") // Headers expuestos en las respuestas
                .allowCredentials(false) // Permite el uso de credenciales (cookies, headers de autenticación)
                .maxAge(3600); // Tiempo en segundos que el navegador cachea la configuración de CORS
    }

}
