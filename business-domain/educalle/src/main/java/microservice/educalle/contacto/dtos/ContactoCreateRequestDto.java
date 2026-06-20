package microservice.educalle.contacto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.shared_data.entities.AliadoEntity;
import microservice.shared_data.entities.DocumentoEntity;
import microservice.shared_data.entities.PaisEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoCreateRequestDto {

   @NotNull
   private AliadoEntity aliado;

   @NotNull
   private DocumentoEntity tipoDoc;

   @NotNull
   private PaisEntity nacionalidad;

   @NotBlank
   private String numeroDoc;

   @NotBlank
   private String nombres;

   // ! @NotBlank
   private String primerApe;

   private String segundoApe;
   private String telefono;
   private String direccion;
   private String correo;

   @NotNull
   private Integer usuRegistra;

}
