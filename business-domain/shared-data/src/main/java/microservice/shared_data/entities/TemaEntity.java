package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_TEMAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idTema" })
public class TemaEntity {

   @Id
   @Column(name = "TE_ID_TEMA")
   private Integer idTema;

   @OneToOne
   @JoinColumn(name = "UN_ID_UNIDAD")
   private UnidadEntity unidad;

   @OneToMany(mappedBy = "tema", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "tema" })
   private List<TallerEntity> talleres;

   @Column(name = "TE_NOMBRE")
   private String nombre;

   @Column(name = "TE_DESCRIPCION")
   private String descripcion;

   @Column(name = "TE_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "TE_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "TE_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "TE_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "TE_ESTADO")
   private Integer estado;

   @Column(name = "TE_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

}