package microservice.shared_data.entities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ANEXOS_RESPUESTAS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idRespuesta" })
public class AnexoRespuestaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "AR_ID_RESPUESTA")
   private Long idRespuesta;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PF_ID_FAMILIA")
   @JsonIgnoreProperties(value = { "unidadOrganica", "servicio", "anexosRespuestas", "integrantesFamilia" })
   private PotencialFamiliaEntity familia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "FI_ID_INTEGRANTE")
   @JsonIgnoreProperties(value = { "familia", "anexosRespuestas" })
   private IntegranteFamiliaEntity integrante;

   @Column(name = "AR_DESTINATARIO")
   private Integer destinatario;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "AP_ID_PREGUNTA")
   private AnexoPregutasEntity pregunta;

   @Column(name = "AR_RESPUESTA")
   private String respuesta;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   @Column(name = "AR_RESPUESTA_BLOB")
   private byte[] archivo;

   @Column(name = "AR_OBSERVACION")
   private String observacion;

   @Column(name = "SF_ID_FASE")
   private Integer fase;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PR_ID_PERSONAL")
   private PersonalEntity personal;

   @Column(name = "AR_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "AR_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "AR_USU_MODIFICA")
   private Integer usuModifica;

   @Column(name = "AR_FECHA_MODIFICA")
   private LocalDate fechaModifica;

   @Column(name = "AR_USU_ELIMINA")
   private Integer usuElimina;

   @Column(name = "AR_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "AR_ELIMINADO")
   private Integer eliminado;

   @Column(name = "AN_ESTADO")
   private Integer estado;

   @PrePersist
   private void prePersist() {

      // * Común
      this.setEstado(1);
      this.setEliminado(0);

      if (this.fechaRegistra == null) { // * Si fecha de registro es nulo, será la fecha actual
         this.setFechaRegistra(LocalDate.now());
      }

      if (this.destinatario == null) { // ! Si destinatario es nulo, será 1 ↔ FAMILIA
         this.setDestinatario(1);
      }
   }

   @PreUpdate
   private void preUpdate() {
      this.setFechaModifica(LocalDate.now());
   }

}
