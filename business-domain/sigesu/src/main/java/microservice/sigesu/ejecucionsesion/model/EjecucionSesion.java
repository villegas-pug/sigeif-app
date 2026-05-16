package microservice.sigesu.ejecucionsesion.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.catalogo.model.Catalogo;
import microservice.sigesu.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.sigesu.patfam.models.DetPatfam;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idEjecucion" })
public class EjecucionSesion {

   private Long idEjecucion;
   private DetPatfam detPatfam;
   private UnidadSesion sesion;
   private Personal personal;
   private List<EjecucionSesionIntegrante> integrantes;
   private Catalogo modalidad;
   private LocalDateTime fecHoraIni;
   private LocalDateTime fecHoraFin;

   // ! Nuevo: Catálogo de fichas
   private Integer integrantesPresentes;
   private String integrantesAusentes;
   private Integer parejaPreparadaSesion;
   private String lugarEspacio;
   private String motivoFueraCasa;
   private Integer miembrosAseados;
   private Integer espacioOrdenado;
   private Integer espacioLimpio;
   private String actividadesSonRealizadas;
   private LocalDateTime fecHoraSiguienteSesion;
   private String compromiso;
   private String observaciones;
   private String anexoNombre;
   private byte[] anexo;

   private Integer usuRegistra;
   private LocalDate fechaRegistra;
   private Integer usuModifica;
   private LocalDate fechaModifica;
   private Integer usuarioElimina;
   private LocalDate fechaElimina;
   private Integer realizoSesion;
   private Integer estado;
   private Integer eliminado;

}
