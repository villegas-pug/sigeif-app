package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.List;
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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_UNIDAD_SESIONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idSesion" })
public class UnidadSesionEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "SE_ID_SESION")
   private Integer idSesion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "UN_ID_UNIDAD")
   private UnidadEntity unidad;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MO_ID_MODULO")
   @JsonIgnoreProperties(value = { "objetivo", "unidades", "talleres" })
   private ModuloEntity modulo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "OE_ID_OBJETIVO")
   @JsonIgnoreProperties(value = { "modulos", "talleres" })
   private ObjetivoEspecificoEntity objetivo;

   @OneToMany(mappedBy = "sesion", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "sesion" })
   private List<TallerEntity> talleres;

   @Column(name = "SE_NOMBRE")
   private String nombre;

   @Column(name = "SE_DESCRIPCION")
   private String descripcion;

   @Column(name = "SE_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "SE_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "SE_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "SE_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "SE_ESTADO")
   private Integer estado;

   @Column(name = "SE_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

}
