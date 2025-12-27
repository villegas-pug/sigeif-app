package microservice.punche.aliado.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.contacto.dtos.ContactoDto;
import microservice.punche.gruposocial.model.GrupoSocial;
import microservice.punche.institucion.model.Institucion;
import microservice.punche.ubigeo.model.UbigeoNombre;
import microservice.punche.zona.model.ZonaIntervencion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aliado {
   private Long idAliado;
   private ZonaIntervencion zonaIntervencion;
   private Institucion institucion;
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

   // * Nuevo:
   private UbigeoNombre ubigeo;
   private String tipoAliado;
   private String direccion;
   private String telefono;
   private String correo;
   private String representante;
}
