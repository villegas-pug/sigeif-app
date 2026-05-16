package microservice.sigesu.reporting.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompromisoFamiliarReportingDto {

   private String nombresCuidador;
   private String numDocCuidador;
   private String nombresAcompañante;
   private String numDocAcompañante;
   private LocalDateTime fechaCompromiso;

}
