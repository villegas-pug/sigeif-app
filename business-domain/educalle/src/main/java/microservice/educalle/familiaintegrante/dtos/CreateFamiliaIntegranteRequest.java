package microservice.educalle.familiaintegrante.dtos;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.potencialfamilia.model.PotencialFamilia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFamiliaIntegranteRequest {

   private Long idIntegrante;
   private Long idFamilia;
   private PotencialFamilia familia;
   private Integer idTipdoc;
   private Integer idGradoInst;
   private Integer idTipoSeguro;
   private Integer idNac;
   private Integer idPaisNacimiento;
   private Integer idParentesco;
   private Integer idEstadoCivil;
   private Integer idSexo;
   private Long idIdioma;
   private Integer tieneDiscapacidad;
   private Long idDiscapacidad;
   private Long idDerivadoPor;
   private Long idServicioCuidador;
   private Integer idCentroPobla;
   private Integer idOcupacion;
   private Long idTipoFamilia;
   private Long idEtnia;
   private Long idLenguaMaterna;
   private Long idTipoVivienda;
   private Long idUbicacionVivienda;
   private Integer cantIntegrantes;
   private Integer cantNNA;
   private String numeroDoc;
   private String nombres;
   private String primerApe;
   private String segundoApe;
   private String apellidoCasado;
   private LocalDate fecNac;
   private Integer edad;
   private String telefono;
   private String correo;
   private String idDepartamento;
   private String idProvincia;
   private String idDistrito;
   private String direccion;
   private String referenciaDomiciliaria;
   private String gradoSeccionNNA;
   private Integer anioAnteriorPromovido;
   private String nombreInstitucionEducativa;
   private String peso;
   private String talla;
   private Double ingresosSoles;
   private Double gastosSoles;

   // * Nuevo:
   // --------------------------------------------
   private String centroPoblado;
   // --------------------------------------------

   @NotNull
   private Integer cuidador; // Cuidador: 1; NNA: 2, Otros: 0

   private LocalDate fecActualiza;
   private Integer usuRegistra;

}
