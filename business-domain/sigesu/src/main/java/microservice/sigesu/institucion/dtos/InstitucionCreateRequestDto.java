package microservice.sigesu.institucion.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.ubigeo.model.UbigeoNombre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitucionCreateRequestDto {

   @NotBlank
   private String nombreReferencia;

   @NotBlank
   private String direccion;

   @NotBlank
   private String referencia;

   @NotBlank
   private String telefono;

   @NotBlank
   private String correo;

   @NotBlank
   private String representante; // INSREPRESENTANTE

   @NotBlank
   private String ruc; // INSRUC

   @NotBlank
   private String contacto; // INSCONTACTO

   @NotBlank
   private UbigeoNombre ubigeo; // INSCONTACTO

}
