package microservice.shared_data.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ANEXOS_PREGUNTAS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(of = { "idPregunta" })
public class AnexoPregutasEntity {

   @Id
   @Column(name = "AP_ID_PREGUNTA")
   private Long idPregunta;

   @Column(name = "SI_ID_SERVICIO")
   private Integer idServicio;

   @Column(name = "AP_NUM_ANEXO")
   private Integer numAnexo;

   @Column(name = "AP_NUM_GRUPO")
   private Integer numGrupo;

   @Column(name = "AP_NUM_PREGUNTA")
   private Integer numPregunta;

   @Column(name = "AP_PREGUNTA")
   private String pregunta;

   @Column(name = "AP_OPCIONES")
   private String opciones;

   @Column(name = "AP_TIPO_CONTROL")
   private String tipoControl;

   @Column(name = "AP_CONDICION_SI")
   private Integer condicionSi;

   @Column(name = "AP_CONDICION_NO")
   private Integer condicionNo;

   @Column(name = "AP_FECHA_REGISTRO")
   private LocalDate fechaRegistro;

   @Column(name = "AP_FECHA_ELIMINACION")
   private LocalDate fechaEliminacion;

   @Column(name = "AP_ELIMINADO")
   private Integer eliminado;

}