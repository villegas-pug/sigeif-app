package microservice.educalle.aliado.model;

import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.contacto.dtos.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AliadoDto {

   @NotNull
   private String idInstitucion;

   @NotNull
   private String idGruposocial;

   private Integer gradoinfluencia;
   private Integer interesServicio;
   private Integer resultado;
   private String posicion;

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

   @NotNull
   private Integer idUsuRegistra;

   @Valid
   private List<ContactoDto> contacto;

}