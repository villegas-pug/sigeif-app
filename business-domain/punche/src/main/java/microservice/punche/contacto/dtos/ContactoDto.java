package microservice.punche.contacto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoDto {

   @NotNull
   private Integer idDocumento;

   @NotNull
   private Integer idNacionalidad;

   @NotBlank
   private String numeroDoc;

   @NotBlank
   private String nombres;

   // ! @NotBlank
   private String primerApe;
   private String segundoApe;
   /*
    * private LocalDate fecNac;
    * private String sexo;
    */
   @NotBlank
   private String telefono;

   @NotBlank
   private String direccion;

   @NotNull
   private Integer idUsuRegistra;

   @NotBlank
   private String correo;

}