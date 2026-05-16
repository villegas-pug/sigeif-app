package microservice.sigesu.anexorespuesta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class StaticConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///C:/data/sigesu/uploads/");
                System.out.println("Cargando StaticConfig para uploads...");
    }
    
}