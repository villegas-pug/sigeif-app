package microservice.punche.zona.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;
import microservice.punche.institucion.model.Institucion;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.servicio.model.Servicio;
import microservice.punche.ubigeo.model.UbigeoNombre;
import microservice.punche.unidadorganica.model.UnidadOrganica;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencion {

   private Long idZona;
   private Servicio servicio;
   private Set<EquipoTrabajo> equiposTrabajo;
   private Set<Aliado> aliados;
   private Set<PotencialFamilia> potencialesFamilias;
   private UnidadOrganica unidadOrganica;
   private Institucion institucion;
   private UnidadOrganica unidadOrg;
   private UbigeoNombre ubigeo;
   private String idUbigeo;
   private Integer codTipo;
   private String descripcion;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

   // ? Nuevo

}
