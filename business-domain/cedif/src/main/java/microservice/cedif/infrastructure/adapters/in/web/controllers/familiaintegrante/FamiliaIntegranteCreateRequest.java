package microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.domain.models.PotencialFamilia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaIntegranteCreateRequest {

   // @NotNull
   private PotencialFamilia familia;

   private Long idFamilia;

   @NotNull
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

   @NotBlank
   private String numeroDoc;

   @NotBlank
   private String nombres;

   @NotBlank
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

   @NotNull
   private Integer cuidador; // Cuidador: 1; NNA: 2, Otros: 0

   @NotNull
   private Integer usuRegistra;

}
