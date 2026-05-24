package microservice.shared_data.entities;

import java.time.LocalDate;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ANEXO_CABECERA_AUDIO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idAudio" })
public class AnexoCabeceraAudio {

   @Id
   @Column(name = "ACA_ID_AUDIO")
   private Long idAudio;

   @Column(name = "ID_ANEXO_CABECERA")
   private Long idAnexoCabecera;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   @Column(name = "ACA_AUDIO")
   private byte[] audio;

   @Column(name = "ACA_NOMBRE_ARCHIVO")
   private String nombreArchivo;

   @Column(name = "ACA_FECHA_REGISTRO")
   private LocalDate fechaRegistro;

   @Column(name = "ACA_ESTADO")
   private Integer estado;

   @Column(name = "ACA_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      if (this.fechaRegistro == null) {
         this.setFechaRegistro(LocalDate.now());
      }

      if (this.estado == null) {
         this.setEstado(1);
      }

      if (this.eliminado == null) {
         this.setEliminado(0);
      }
   }

}
