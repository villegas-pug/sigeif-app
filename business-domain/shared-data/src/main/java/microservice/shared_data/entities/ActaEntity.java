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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ACTAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idActa" })
public class ActaEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "AC_ID_ACTA")
   private Long idActa;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "AL_ID_ALIADO")
   @JsonIgnoreProperties(value = { "zonaIntervencion", "institucion", "grupoSocial", "contactos" })
   private AliadoEntity aliado;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "CA_ID_TIPO_ACTA")
   private CatalogoEntity tipoActa;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   @Column(name = "AC_ANEXO")
   private byte[] anexo;

   @Column(name = "AC_ANEXO_NOMBRE")
   private String anexoNombre;

   @Column(name = "AC_FECHA_ACTA")
   private LocalDate fechaActa;

   @Column(name = "AC_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "AC_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "AC_ESTADO")
   private Integer estado;

   @Column(name = "AC_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

}