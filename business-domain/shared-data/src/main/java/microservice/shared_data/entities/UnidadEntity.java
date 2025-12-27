package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_UNIDADES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idUnidad" })
public class UnidadEntity {

   @Id
   @Column(name = "UN_ID_UNIDAD")
   private Integer idUnidad;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MO_ID_MODULO")
   private ModuloEntity modulo;

   @OneToMany(mappedBy = "unidad", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "unidad" })
   private List<TemaEntity> temas;

   @OneToMany(mappedBy = "unidad", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "unidad" })
   private List<UnidadSesionEntity> sesiones;

   @Column(name = "UN_NOMBRE")
   private String nombre;

   @Column(name = "UN_DESCRIPCION")
   private String descripcion;

   @Column(name = "UN_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "UN_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "UN_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "UN_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "UN_ESTADO")
   private Integer estado;

   @Column(name = "UN_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

}
