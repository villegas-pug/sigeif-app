package microservice.educalle.zona.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.aliado.model.Aliado;
import microservice.educalle.equipotrabajo.model.EquipoTrabajo;
import microservice.educalle.institucion.model.Institucion;
import microservice.educalle.potencialfamilia.model.PotencialFamilia;
import microservice.educalle.servicio.model.Servicio;
import microservice.educalle.ubigeo.model.UbigeoNombre;
import microservice.educalle.unidadorganica.model.UnidadOrganica;

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
