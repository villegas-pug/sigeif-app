package microservice.educalle.aliado.dtos;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.contacto.dtos.ContactoDto;
import microservice.educalle.gruposocial.model.GrupoSocial;
import microservice.educalle.ubigeo.model.UbigeoNombre;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AliadoResponse {

   private Long idAliado;
   // private ZonaIntervencion zonaIntervencion;
   // private Institucion institucion;

   // * Transient:
   private Long idInstitucion;
   private String nombreInstitucion;

   // * Nuevo:
   private UbigeoNombre ubigeo;
   private String tipoAliado;
   private String direccion;
   private String telefono;
   private String correo;
   private String representante;
   // -----------------------------------------

   private GrupoSocial grupoSocial;
   private List<ContactoDto> contactos;
   private Integer gradoInfluencia;
   private Integer interesServicio;
   private Integer resultado;
   private String posicion;

   private Long usuRegistra;
   private LocalDate fecRegistra;
   private Long usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

}
