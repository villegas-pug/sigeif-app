package microservice.punche.familiaintegrante.dtos;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.catalogo.model.Catalogo;
import microservice.punche.documento.model.Documento;
import microservice.punche.pais.model.Pais;
import microservice.punche.ubigeo.model.UbigeoNombre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaIntegranteResponse {

   private Long idIntegrante;
   private Documento tipdoc;
   private Catalogo gradoInst;
   private Catalogo tipoSeguro;
   private Pais nacionalidad;
   private Pais paisNacimiento;
   private Catalogo parentesco;
   private Catalogo estadoCivil;
   private Catalogo sexo;
   private Catalogo ocupacion;
   private UbigeoNombre ubigeoNac;
   private UbigeoNombre ubigeo;

   private Set<AnexoRespuesta> anexosRespuestas;

   private Long IdCentroPobla;
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
   private Integer cuidador;

   // * Nuevo:
   // --------------------------------------------
   private String centroPoblado;
   // --------------------------------------------

   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

}
