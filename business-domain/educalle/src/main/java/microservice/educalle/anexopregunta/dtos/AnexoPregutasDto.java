package microservice.educalle.anexopregunta.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoPregutasDto {

   private Long idPregunta;
   private Integer idServicio;
   private Integer numAnexo;
   private Integer numGrupo;
   private Integer numPregunta;
   private String pregunta;
   private String opciones;
   private String tipoControl;
   private Integer obligatoria;
   private String pregunta2;
   private String tipoControl2;
   private String opciones2;
   private Integer obligatoria2;
   private String tipoDato1;
   private String tipoDato2;
   private String condicion;

   private String modoControl;
   private String vistaControl;
   private String editable;
   private Integer reqDisparador;
   private String urlServicio;
   private String httpMetodo;
   private String httpParams;
   private String iconoControl;
   private String editableBifurcaciones;
   private Integer lng;
   private Integer lngBifurcacion;
   private String rangoLongitud;
   private Integer reqAlfNum;
   private Integer reqContador;
   private Integer reqObligatoria1Cierre;
   private Integer reqObligatoria2Cierre;
   private String bloqSubmitSiInvalido;

   // * Nuevo
   private String defaultValueBifurcaciones;
}