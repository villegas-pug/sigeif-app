package microservice.punche.zona.dtos;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencionResponse {

   private Long idZona;
   private LocalDate fechaRegistra;
   private String descripcion;
   private Integer idServicio;
   private String idUbigeo;
   private Integer estado;
   private Integer eliminado;

   private Integer idInstitucion;
   private Integer idUnidadorg;
   private Integer codTipo;
   private Integer idUsuRegistra;

   private String coordinador;
   private String idDepartamento;
   private String departamento;
   private String idProvincia;
   private String provincia;
   private String idDistrito;
   private String distrito;

}