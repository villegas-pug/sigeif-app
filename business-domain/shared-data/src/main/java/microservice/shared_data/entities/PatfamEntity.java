package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_PATFAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idPatfam" })
public class PatfamEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PA_ID_PATFAM")
   private Long idPatfam;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PF_ID_FAMILIA")
   @JsonIgnoreProperties(value = { "zonaIntervencion", "aliado", "unidadOrganica", "servicio", "anexosRespuestas",
         "integrantesFamilia", "motivosReferencia" })
   private PotencialFamiliaEntity familia;

   @OneToMany(mappedBy = "patfam", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "patfam" })
   private List<DetPatfamEntity> detPatfam;

   @Column(name = "PA_MOTIVO_REFERENCIA")
   private String motivoReferencia;

   @Column(name = "PA_DIAGNOSTICO")
   private String diagnostico;

   @Column(name = "PA_NOMBRE_CUIDADOR")
   private String nombreCuidador;

   @Column(name = "PA_ZONA_INTERVENCION")
   private String zonaIntervencion;

   @Column(name = "PA_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "PA_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "PA_USU_MODIFICA")
   private Integer usuModifica;

   @Column(name = "PA_FECHA_MODIFICA")
   private LocalDate fechaModifica;

   @Column(name = "PA_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "PA_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "PA_ESTADO")
   private Integer estado;

   @Column(name = "PA_ELIMINADO")
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