package microservice.shared_data.entities;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_TALLERES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idTaller" })
public class TallerEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "TA_ID_TALLER")
   private Integer idTaller;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "OE_ID_OBJETIVO")
   @JsonIgnoreProperties(value = { "servicio", "modulos", "unidades", "talleres" })
   private ObjetivoEspecificoEntity objetivoEspecifico;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MO_ID_MODULO")
   @JsonIgnoreProperties(value = { "objetivo", "unidades" })
   private ModuloEntity modulo;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SE_ID_SESION")
   @JsonIgnoreProperties(value = { "unidad", "talleres" })
   private UnidadSesionEntity sesion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "TE_ID_TEMA")
   @JsonIgnoreProperties(value = { "talleres", "unidad" })
   private TemaEntity tema;

   @Column(name = "TA_NOMBRE")
   private String nombre;

   @Column(name = "TA_DESCRIPCION")
   private String descripcion;

   @Column(name = "TA_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "TA_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "TA_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "TA_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "TA_ESTADO")
   private Integer estado;

   @Column(name = "TA_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFechaRegistra(LocalDate.now());
      this.setEliminado(0);
      this.setEstado(1);
   }

}