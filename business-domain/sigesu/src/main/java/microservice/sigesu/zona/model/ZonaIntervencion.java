package microservice.sigesu.zona.model;

import java.time.LocalDate;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.aliado.model.Aliado;
import microservice.sigesu.equipotrabajo.model.EquipoTrabajo;
import microservice.sigesu.institucion.model.Institucion;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.sigesu.servicio.model.Servicio;
import microservice.sigesu.ubigeo.model.UbigeoNombre;
import microservice.sigesu.unidadorganica.model.UnidadOrganica;

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
