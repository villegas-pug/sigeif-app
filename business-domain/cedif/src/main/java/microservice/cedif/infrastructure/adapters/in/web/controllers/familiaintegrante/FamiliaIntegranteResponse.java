package microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.domain.models.Catalogo;
import microservice.cedif.domain.models.Documento;
import microservice.cedif.domain.models.Pais;
import microservice.cedif.domain.models.UbigeoNombre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaIntegranteResponse {

   private Long idIntegrante;
   private String codIntegrante;
   private Documento tipdoc;
   private Catalogo gradoInst;
   private Catalogo tipoSeguro;
   private Pais nacionalidad;
   private Pais paisNacimiento;
   private Catalogo parentesco;
   private Catalogo estadoCivil;
   private Catalogo sexo;
   private Long IdCentroPobla;
   private Catalogo ocupacion;
   private UbigeoNombre ubigeo;
   private String numeroDoc;
   private String nombres;
   private String primerApe;
   private String segundoApe;
   private String apellidoCasado;
   private LocalDate fecNac;
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
   private UbigeoNombre ubigeoNac;
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

   private Integer cuidador;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer integranteApto;
   private Integer estado;
   private Integer eliminado;

}
