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
@Table(name = "SSI_OBJETIVOS_ESPECIFICOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idObjetivo" })
public class ObjetivoEspecificoEntity {

   @Id
   @Column(name = "OE_ID_OBJETIVO")
   private Integer idObjetivo;

   @OneToOne
   @JoinColumn(name = "SI_ID_SERVICIO")
   private ServicioEntity servicio;

   @OneToMany(mappedBy = "Objetivo", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "Objetivo" })
   private List<ModuloEntity> modulos; // ! Punche, Cedif

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "objetivo")
   @JsonIgnoreProperties(value = { "unidad", "modulo", "objetivo", "talleres" })
   private List<UnidadSesionEntity> sesiones; // ! Acercandonos

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "objetivoEspecifico")
   @JsonIgnoreProperties(value = { "objetivoEspecifico", "unidad", "sesion" })
   private List<TallerEntity> talleres; // ! Acercandonos

   @Column(name = "OE_NOMBRE")
   private String nombre;

   @Column(name = "OE_DESCRIPCION")
   private String descripcion;

   @Column(name = "OE_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "OE_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "OE_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "OE_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "OE_ESTADO")
   private Integer estado;

   @Column(name = "OE_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
   }

}
