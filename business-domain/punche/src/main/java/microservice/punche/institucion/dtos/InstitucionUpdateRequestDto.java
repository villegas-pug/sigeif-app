package microservice.punche.institucion.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.ubigeo.model.UbigeoNombre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstitucionUpdateRequestDto {

   @NotNull
   private Long idInstitucion;

   private String nombreReferencia;
   private String direccion;
   private String referencia;
   private String telefono;
   private String correo;
   private String representante; // INSREPRESENTANTE
   private String ruc; // INSRUC
   private String contacto; // INSCONTACTO
   private UbigeoNombre ubigeo; // INSCONTACTO

}
