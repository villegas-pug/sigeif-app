package microservice.punche.familiaintegrante.dtos;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.potencialfamilia.model.PotencialFamilia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFamiliaIntegranteRequest {

   // @NotNull
   private Long idIntegrante;

   private Long idFamilia;
   private PotencialFamilia familia;
   private Integer idTipdoc;
   private Integer idGradoInst;
   private Integer idTipoSeguro;
   private Integer idNac;
   private Integer idPaisNacimiento;

   // ? Nuevo
   private String idDepartamentoNac;
   private String idProvinciaNac;
   private String idDistritoNac;

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

   @NotNull
   private Integer cuidador;

   private Long idCentroPobla;
   private String gradoSeccionNNA;
   private Integer idOcupacion;
   private Long idTipoFamilia;
   private Long idEtnia;
   private Long idLenguaMaterna;
   private Long idTipoVivienda;
   private Long idUbicacionVivienda;
   private Integer cantIntegrantes;
   private Integer cantNNA;
   private String telefono;
   private String correo;
   private String idDepartamento;
   private String idProvincia;
   private String idDistrito;
   private Long idIdioma;
   private Long idDiscapacidad;
   private Long idDerivadoPor;
   private Long idServicioCuidador;
   private Integer anioAnteriorPromovido;
   private String nombreInstitucionEducativa;
   private String peso;
   private String talla;
   private Double ingresosSoles;
   private Double gastosSoles;

   // * Nuevo:
   // --------------------------------------------
   private String centroPoblado;
   private String conadis;
   private String gradoDiscapacidad;
   private Integer tieneDiscapacidad;
   // --------------------------------------------

   // Crea y actualiza
   private Integer usuActualiza;
   private Integer usuRegistra;

}
