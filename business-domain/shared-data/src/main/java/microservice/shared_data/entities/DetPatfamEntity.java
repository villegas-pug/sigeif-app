package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_DET_PATFAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idDetPatfam" })
public class DetPatfamEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "DP_ID_DET_PATFAM")
   private Long idDetPatfam;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PA_ID_PATFAM")
   private PatfamEntity patfam;

   @OneToMany(mappedBy = "detPatfam", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "detPatfam", "sesion" })
   private Set<EjecucionSesionEntity> ejecucionSesiones;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "OE_ID_OBJETIVO")
   private ObjetivoEspecificoEntity objetivo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MO_ID_MODULO")
   private ModuloEntity modulo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "UN_ID_UNIDAD")
   private UnidadEntity unidad;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "TE_ID_TEMA")
   private TemaEntity tema;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SE_ID_SESION")
   private UnidadSesionEntity sesion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "TA_ID_TALLER")
   private TallerEntity taller;

   @Column(name = "DP_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "DP_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "DP_USU_MODIFICA")
   private Integer usuModifica;

   @Column(name = "DP_FECHA_MODIFICA")
   private LocalDate fechaModifica;

   @Column(name = "DP_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "DP_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "DP_ESTADO")
   private Integer estado;

   @Column(name = "DP_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

   @PreUpdate
   private void preUpdate() {
      this.setFechaModifica(LocalDate.now());
   }

}
