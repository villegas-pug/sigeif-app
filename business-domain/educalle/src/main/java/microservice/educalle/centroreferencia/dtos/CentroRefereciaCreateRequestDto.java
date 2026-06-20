package microservice.educalle.centroreferencia.dtos;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentroRefereciaCreateRequestDto {

   @NotBlank
   private String nombre;

   @NotBlank
   private String representante;

   @NotBlank
   private String ruc;

   @NotBlank
   private String contacto;

   @NotBlank
   @Length
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
