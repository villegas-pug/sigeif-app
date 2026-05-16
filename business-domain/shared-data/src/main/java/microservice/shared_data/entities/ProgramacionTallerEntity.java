package microservice.shared_data.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
@Table(name = "SSI_PROG_TALLERES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idProgTaller" })
public class ProgramacionTallerEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "PT_ID_PROG_TALLER")
   private Long idProgTaller;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "TA_ID_TALLER")
   private TallerEntity taller;

   @OneToMany(mappedBy = "progTaller", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "progTaller" })
   private List<ProgramacionTallerFamiliaEntity> tallerFamilias;

   // ! Nuevo
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PE_ID_PERSONAL")
   private PersonalEntity personal; // * ¿Personal que dicta?

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "UO_ID_UNIDADORG")
   @JsonIgnoreProperties(value = { "potencialesFamilias" })
   private UnidadOrganicaEntity unidadorg; // * ¿Empresa que dicta?

   @Column(name = "PT_ANEXO_NOMBRE")
   private String anexoNombre;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   @Column(name = "PT_ANEXO")
   private byte[] anexo;

   // !

   @Column(name = "PT_TEMA")
   private String tema;

   @Column(name = "PT_LUGAR_TALLER")
   private String lugarTaller;

   @Column(name = "PT_FEC_HORA_INI")
   private LocalDateTime fecHoraIni;

   @Column(name = "PT_FEC_HORA_FIN")
   private LocalDateTime fecHoraFin;

   @Column(name = "PT_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "PT_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "PT_USU_MODIFICA")
   private Integer usuModifica;

   @Column(name = "PT_FECHA_MODIFICA")
   private LocalDate fechaModifica;

   @Column(name = "PT_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "PT_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "PT_ESTADO")
   private Integer estado;

   @Column(name = "PT_ELIMINADO")
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
