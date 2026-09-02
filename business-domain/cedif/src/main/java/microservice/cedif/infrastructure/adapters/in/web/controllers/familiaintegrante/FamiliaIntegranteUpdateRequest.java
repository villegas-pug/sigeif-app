package microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.domain.models.PotencialFamilia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaIntegranteUpdateRequest {

   private Long idIntegrante;
   private PotencialFamilia familia;
   private Long idFamilia;
   private Integer idTipdoc;
   private Integer idGradoInst;
   private Integer idTipoSeguro;
   private Integer idNac;
   private Integer idPaisNacimiento;
   private Integer idParentesco;
   private Integer idEstadoCivil;
   private Integer idSexo;
   private String numeroDoc;
   private String nombres;
   private String primerApe;
   private String segundoApe;
   private String apellidoCasado;
   private LocalDate fecNac;
   private Integer edad;
   private String direccion;
   private String referenciaDomiciliaria;
   private Integer anioAnteriorPromovido;
   private String nombreInstitucionEducativa;
   private String peso;
   private String talla;
   private Double ingresosSoles;
   private Double gastosSoles;

   @NotNull
   private Integer cuidador;

   private Integer usuActualiza;
   private Integer usuRegistra;
   private Long idIdioma;
   private Long idDiscapacidad;
   private Integer tieneDiscapacidad;
   private Long idDerivadoPor;
   private Long idServicioCuidador;
   private Long idCentroPobla;
   private String gradoSeccionNNA;
   private Integer idOcupacion;
   private String telefono;
   private String correo;
   private String idDepartamento;
   private String idProvincia;
   private String idDistrito;
   private String idDepartamentoNac;
   private String idProvinciaNac;
   private String idDistritoNac;
   private String observaciones;
   private String diagnosticoMedico;
   private String establecimientoSalud;

   private String porCostumbresSeConsidera;
   private String situacionLaboral;
   private String tieneCertMedico;
   private String gradoDiscapacidad;
   private String perfilIngresoNna;
   private String tipoEducacion;
   private String victimaIndirectaFeminicidio;
   private String gestante;
   private String lactante;
   private String inscripcionConadis;
   private String gradoInstruccion;
   private String tipoDiscapacidad;

   // * Nuevo:
   // --------------------------------------------
   private Integer algunIntegranteTieneProblemaSalud;
   private String viaIngresoNnaCedif;
   private String medioIngresoNnaCedif;
   private String descripcionOcupacion;
   private String otraLenguaMaterna;
   private String otraCostumbre;
   private String otraInstitucionDerivadora;
   private String condicionLaboral;
   private String categoriaOcupacional;
   // --------------------------------------------

   private Integer estado;
   private Integer eliminado;

}
