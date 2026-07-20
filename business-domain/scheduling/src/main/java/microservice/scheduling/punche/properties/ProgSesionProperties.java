package microservice.scheduling.punche.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Validated
@Data
@Component
@ConfigurationProperties(prefix = "schedulers.punche.prog-sesion.report")
public class ProgSesionProperties {

   @NotBlank(message = "¡Ruta base es requerida!")
   private String outputPath;

   @Valid
   private ExcelConfig excel = new ExcelConfig();

   @Data
   public static class ExcelConfig {

      @NotBlank(message = "¡Nombre de archivo es requerido!")
      private String outputPath;

      @NotBlank(message = "¡Ruta base es requerida!")
      private String fileName;

   }

}
