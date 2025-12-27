package microservice.cedif.infrastructure.adapters.in.web.controllers.centroreferencia;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentroReferenciaCreateRequest {

   @NotBlank
   private String nombre;

   @NotBlank
   private String representante;

   @NotBlank
   private String ruc;

   @NotBlank
   private String contacto;

   @NotBlank
   @Length(min = 9, max = 9)
   private String telefono;

   @NotBlank
   private String direccion;

   @NotBlank
   private String referencia;

   @NotBlank
   private String correo;

   @NotNull
   private Integer usuRegistra;

   @NotBlank
   private String ubigeo;

}
