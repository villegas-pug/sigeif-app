package microservice.cedif.domain.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idIntegrante" })
public class FamiliaIntegrante {

   private Long idIntegrante;
   private String codIntegrante;
   private Long idFamilia;
   private Long idTipdoc;
   private Long idGradoInst;
   private Long idTipoSeguro;
   private Long idNac;
   private Long idPaisNacimiento;
   private Long idParentesco;
   private Long idEstadoCivil;
   private Long idSexo;
   private PotencialFamilia familia;
   private Documento tipdoc;
   private Catalogo gradoInst;
   private Catalogo tipoSeguro;
   private Pais nacionalidad;
   private Pais paisNacimiento;
   private Catalogo parentesco;
   private Catalogo estadoCivil;
   private Catalogo sexo;
   private Catalogo idioma;
   private Catalogo discapacidad;
   private Catalogo derivadoPor;
   private Catalogo servicioCuidador;
   private Catalogo ocupacion;
   private UbigeoNombre ubigeo;
   private Set<AnexoRespuesta> anexosRespuestas;
   private Integer anioAnteriorPromovido;
   private String nombreInstitucionEducativa;
   private String peso;
   private String talla;
   private Double ingresosSoles;
   private Double gastosSoles;
   private Integer tieneDiscapacidad;
   private Long idIdioma;
   private Long idDiscapacidad;
   private Long idDerivadoPor;
   private Long idServicioCuidador;
   private Long idOcupacion;
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
   private Long idCentroPobla;
   private Integer cuidador;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer integranteApto;
   private Integer estado;
   private Integer eliminado;
   private UbigeoNombre ubigeoNac;
   private String idDepartamentoNac;
   private String idProvinciaNac;
   private String idDistritoNac;
   private String observaciones;
   private String diagnosticoMedico;
   private String establecimientoSalud;

   // * Nuevo:
   // --------------------------------------------
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
   // --------------------------------------------

   // ? Transient
   List<EstadoAnexoProjectionResponse> estadoFichas;

   public String getNombresCompletos() {
      String segundoApe = this.segundoApe != null ? this.segundoApe : "";
      return (this.nombres + " " + this.primerApe + " " + segundoApe).trim();
   }

}
