package microservice.sigesu.programaciontaller.dtos;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.patfam.models.DetPatfam;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.programaciontallerfamilia.model.ProgramacionTallerFamilia;
import microservice.sigesu.taller.model.Taller;
import microservice.sigesu.unidadorganica.model.UnidadOrganica;
import microservice.shared_data.dtos.responses.DetPatfamProjectionResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idProgTaller" })
public class ProgramacionTallerResponse {

   private Long idProgTaller;
   private Taller taller;
   private List<ProgramacionTallerFamilia> tallerFamilias;

   private Personal personal; // * ¿Personal que dicta?
   private UnidadOrganica unidadorg; // * ¿Empresa que dicta?
   private String anexoNombre;

   private String tema;
   private String lugarTaller;
   private LocalDateTime fecHoraIni;
   private LocalDateTime fecHoraFin;
   private Integer estado;
   private Integer eliminado;

   // ! Datos adicionales
   private DetPatfamProjectionResponse detPatfam;

}
