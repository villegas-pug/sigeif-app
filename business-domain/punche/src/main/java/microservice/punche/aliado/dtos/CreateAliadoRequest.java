package microservice.punche.aliado.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAliadoRequest {

   @NotNull
   private Long idZona;

   @NotNull
   private Long idInstitucion;

   @NotNull
   private Long idGrupoSocial;

   @NotNull
   private Integer usuRegistra;

   // * Nuevo:
   @NotBlank
   private String idUbigeo;

   @NotBlank
   private String tipoAliado;

   @NotBlank
   private String direccion;

   private String telefono;
   private String correo;
   private String representante;

}
