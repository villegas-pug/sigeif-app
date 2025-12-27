package microservice.shared_data.entities;

import java.time.LocalDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_EQUIPO_TRABAJO")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idEquipo" })
public class EquipoTrabajoEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "EQ_ID_EQUIPO")
   private Long idEquipo;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ZO_ID_ZONA")
   private ZonaIntervencionEntity zonaIntervencion;

   @OneToOne
   @JoinColumn(name = "PR_ID_PROFESION")
   private Cargo cargo;

   @OneToOne(cascade = CascadeType.MERGE)
   @JoinColumn(name = "PER_ID_PERSONAL")
   private PersonalEntity personal;

   @Column(name = "EQ_USU_REGISTRA")
   private Long usuRegistra;

   @Column(name = "EQ_FEC_REGISTRA")
   private LocalDate fecRegistra;

   @Column(name = "EQ_USU_ACTUALIZA")
   private Long usuActualiza;

   @Column(name = "EQ_FEC_ACTUALIZA")
   private LocalDate fecActualiza;

   @Column(name = "EQ_ESTADO")
   private Integer estado;

   @Column(name = "EQ_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   public void PrePersist() {
      this.setFecRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
   }

   @PreUpdate
   public void preUpdate() {
      this.setFecActualiza(LocalDate.now());
   }

}
