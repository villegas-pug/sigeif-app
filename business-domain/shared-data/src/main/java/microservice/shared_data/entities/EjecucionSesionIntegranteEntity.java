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
@Table(name = "SSI_EJEC_SESION_INTEGRANTES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idSesionIntegrante" })
public class EjecucionSesionIntegranteEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "SI_ID_SESION_INTEGRANTE")
   private Long idSesionIntegrante;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ES_ID_EJECUCION")
   private EjecucionSesionEntity ejecucionSesion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "FI_ID_INTEGRANTE")
   @JsonIgnoreProperties(value = { "familia", "anexosRespuestas" })
   private IntegranteFamiliaEntity integranteFamilia;

   @Column(name = "SI_ASISTIO")
   private @Builder.Default Integer asistio = 0;

   @Column(name = "SI_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "SI_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "SI_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "SI_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "SI_ESTADO")
   private Integer estado;

   @Column(name = "SI_ELIMINADO")
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
   }

}
