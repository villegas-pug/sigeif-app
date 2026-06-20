package microservice.educalle.patfam.dtos;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreatePatfamRequest {

   private Long idPatfam;

   @NotNull
   private Long idFamilia;

   @NotEmpty
   @Valid
   private List<CreateDetPatfamRequest> detPatfam;

   // * Opcionales
   private String motivoReferencia;
   private String diagnostico;
   private String nombreCuidador;
   private String zonaIntervencion;

   @NotNull
   private Integer usuRegistra;

}
