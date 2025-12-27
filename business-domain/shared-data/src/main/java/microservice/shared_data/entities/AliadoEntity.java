package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ALIADOS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idAliado" })
public class AliadoEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "AL_ID_ALIADO")
   private Long idAliado;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ZO_ID_ZONA")
   private ZonaIntervencionEntity zonaIntervencion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "INS_ID_INSTITUCION")
   private InstitucionEntity institucion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "GR_ID_GRUPO_SOCIAL")
   private GrupoSocialEntity grupoSocial;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "aliado", cascade = CascadeType.ALL)
   @JsonIgnoreProperties(value = { "aliado" })
   private List<Contacto> contactos;

   @Column(name = "AL_GRADO_INFLUENCIA")
   private Integer gradoInfluencia;

   @Column(name = "AL_INTERES_SERVICIO")
   private Integer interesServicio;

   @Column(name = "AL_RESULTADO")
   private Integer resultado;

   @Column(name = "AL_POSICION")
   private String posicion;

   // * Nuevo:
   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "UBI_ID_UBIGEO")
   private UbigeoNombreEntity ubigeo;

   @Column(name = "AL_TIPO_ALIADO")
   private String tipoAliado;

   @Column(name = "AL_DIRECCION")
   private String direccion;

   @Column(name = "AL_TELEFONO")
   private String telefono;

   @Column(name = "AL_CORREO")
   private String correo;

   @Column(name = "AL_REPRESENTANTE")
   private String representante;
   // --------------------------------------

   @Column(name = "AL_USU_REGISTRA")
   private Long usuRegistra;

   @Column(name = "AL_FEC_REGISTRA")
   private LocalDate fecRegistra;

   @Column(name = "AL_USU_ACTUALIZA")
   private Long usuActualiza;

   @Column(name = "AL_FEC_ACTUALIZA")
   private LocalDate fecActualiza;

   @Column(name = "AL_ESTADO")
   private Integer estado;

   @Column(name = "AL_ELIMINADO")
   private Integer eliminado;

   @PrePersist()
   public void prePersist() {
      this.setFecRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
   }

   @PreUpdate()
   public void preUpdate() {
      this.setFecActualiza(LocalDate.now());
   }

}
