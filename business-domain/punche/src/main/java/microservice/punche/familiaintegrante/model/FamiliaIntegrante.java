package microservice.punche.familiaintegrante.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.catalogo.model.Catalogo;
import microservice.punche.documento.model.Documento;
import microservice.punche.pais.model.Pais;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.ubigeo.model.UbigeoNombre;
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

   // ? Nuevo
   private String idDepartamentoNac;
   private String idProvinciaNac;
   private String idDistritoNac;

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
   private Catalogo centroPobla;
   private UbigeoNombre ubigeo;
   private UbigeoNombre ubigeoNac;

   private Set<AnexoRespuesta> anexosRespuestas;

   private Long idIdioma;
   private Long idDiscapacidad;
   private Long idDerivadoPor;
   private Long idServicioCuidador;
   private Long idOcupacion;

   // * Nuevo
   private Integer tieneDiscapacidad;
   private String conadis;
   private String gradoDiscapacidad;

   private Catalogo etnia;
   private Catalogo tipoFamilia;
   private Catalogo lenguaMaterna;
   private Catalogo tipoVivienda;
   private Catalogo ubicacionVivienda;
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
   private Long idCentroPobla;
   private Integer cuidador;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

   // * Nuevo:
   // --------------------------------------------
   private String centroPoblado;
   // --------------------------------------------

   // ? Transient
   List<EstadoAnexoProjectionResponse> estadoFichas;

   public String getNombresCompletos() {
      if (this.cuidador == 1)
         return this.nombres;
      String segundoApe = this.segundoApe != null ? this.segundoApe : "";
      return (this.nombres + " " + this.primerApe + " " + segundoApe).trim();
   }

}
