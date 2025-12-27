package microservice.scheduling.punche.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Validated
@Data
@Component
@ConfigurationProperties(prefix = "schedulers.punche.encuesta-satisfaccion.report")
public class EncuestaSatisfaccionProperties {

   @NotNull(message = "¡Ruta base es requerida!")
   private String outputPath;

   @Valid
   private Excel excel = new Excel();

   @Data
   public static class Excel {

      @NotBlank(message = "¡Nombre de archivo es requerido!")
      private String outputPath;

      @NotBlank(message = "¡Ruta base es requerida!")
      private String fileName;

   }

}
