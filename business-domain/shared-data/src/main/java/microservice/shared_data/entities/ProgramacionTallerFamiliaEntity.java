package microservice.shared_data.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "SSI_PROG_TALLER_FAMILIAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idProgTallFam" })
public class ProgramacionTallerFamiliaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PD_ID_PROG_TALL_FAM")
   private Long idProgTallFam;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PT_ID_PROG_TALLER")
   private ProgramacionTallerEntity progTaller;

   // ! Eliminado del modelo
   // @ManyToOne(fetch = FetchType.EAGER)
   // @JoinColumn(name = "DP_ID_DET_PATFAM")
   // @JsonIgnoreProperties(value = { "patfam", "ejecucionSesiones" })
   // private DetPatfamEntity detPatfam; */

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PF_ID_FAMILIA")
   @JsonIgnoreProperties(value = { "zonaIntervencion", "aliado", "unidadOrganica", "servicio", "anexosRespuestas",
         "motivosReferencia" })
   private PotencialFamiliaEntity familia;

   @Column(name = "PD_ASISTIO")
   private @Builder.Default Integer asistio = 0;

   @Column(name = "PD_INTEGRANTES_ASISTIERON")
   private String integrantesAsistieron;

   @Column(name = "PD_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "PD_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "PD_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "PD_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "PD_ESTADO")
   private Integer estado;

   @Column(name = "PD_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

   @PreUpdate
   private void preUpdate() {
      if (this.getUsuarioElimina() != null) {
         this.setFechaElimina(LocalDate.now());
      }

      if (this.eliminado == 1) {
         this.setUsuarioElimina(this.getUsuRegistra());
         this.setFechaElimina(LocalDate.now());
      }
   }

}