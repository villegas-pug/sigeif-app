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
@Table(name = "SSI_MODULOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idModulo" })
public class ModuloEntity {

   @Id
   @Column(name = "MO_ID_MODULO")
   private Integer idModulo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "OE_ID_OBJETIVO")
   private ObjetivoEspecificoEntity Objetivo;

   @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "modulo", "temas", "sesiones" })
   private List<UnidadEntity> unidades;

   @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "unidad", "modulo", "objetivo", "talleres" })
   private List<UnidadSesionEntity> sesiones;

   @OneToMany(mappedBy = "modulo", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "objetivoEspecifico", "modulo", "sesion" })
   private List<TallerEntity> talleres;

   @Column(name = "MO_NOMBRE")
   private String nombre;

   @Column(name = "MO_DESCRIPCION")
   private String descripcion;

   @Column(name = "MO_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "MO_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "MO_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "MO_FECHA_ELIMINA")
   private LocalDate fecha_elimina;

   @Column(name = "MO_ESTADO")
   private Integer estado;

   @Column(name = "MO_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
   }

}
