package microservice.shared_data.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_EJECUCION_SESIONES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idEjecucion" })
public class EjecucionSesionEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ES_ID_EJECUCION")
   private Long idEjecucion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "DP_ID_DET_PATFAM")
   @JsonIgnore
   private DetPatfamEntity detPatfam;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SE_ID_SESION")
   private UnidadSesionEntity sesion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PR_ID_PERSONAL")
   private PersonalEntity personal;

   @OneToMany(mappedBy = "ejecucionSesion", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "ejecucionSesion" })
   private Set<EjecucionSesionIntegranteEntity> integrantes;

   @OneToOne
   @JoinColumn(name = "CA_ID_MODALIDAD")
   private CatalogoEntity modalidad;

   @Column(name = "ES_FEC_HORA_INI")
   private LocalDateTime fecHoraIni;

   @Column(name = "ES_FEC_HORA_FIN")
   private LocalDateTime fecHoraFin;

   // ! Nuevo: Catálogo de fichas
   @Column(name = "ES_INTEGRANTES_PRESENTES")
   private Integer integrantesPresentes;

   @Column(name = "ES_INTEGRANTES_AUSENTES")
   private String integrantesAusentes;

   @Column(name = "ES_PAREJA_PREPARADA_SESION")
   private Integer parejaPreparadaSesion;

   @Column(name = "ES_LUGAR_ESPACIO")
   private String lugarEspacio;

   @Column(name = "ES_MOTIVO_FUERA_CASA")
   private String motivoFueraCasa;

   @Column(name = "ES_MIEMBROS_ASEADOS")
   private Integer miembrosAseados;

   @Column(name = "ES_ESPACIO_ORDENADO")
   private Integer espacioOrdenado;

   @Column(name = "ES_ESPACIO_LIMPIO")
   private Integer espacioLimpio;

   @Column(name = "ES_ACTIVIDADES_SON_REALIZADAS")
   private String actividadesSonRealizadas;

   @Column(name = "ES_FEC_HORA_SIGUIENTE_SESION")
   private LocalDateTime fecHoraSiguienteSesion;

   @Column(name = "ES_COMPROMISO")
   private String compromiso;

   @Column(name = "ES_OBSERVACIONES")
   private String observaciones;

   @Column(name = "ES_ANEXO_NOMBRE")
   private String anexoNombre;

   @Lob
   @Basic(fetch = FetchType.LAZY)
   @Column(name = "ES_ANEXO")
   private byte[] anexo;

   // !

   @Column(name = "ES_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "ES_FECHA_REGISTRA")
   private LocalDate fechaRegistra;

   @Column(name = "ES_USU_MODIFICA")
   private Integer usuModifica;

   @Column(name = "ES_FECHA_MODIFICA")
   private LocalDate fechaModifica;

   @Column(name = "ES_USUARIO_ELIMINA")
   private Integer usuarioElimina;

   @Column(name = "ES_FECHA_ELIMINA")
   private LocalDate fechaElimina;

   @Column(name = "ES_REALIZO_SESION")
   private Integer realizoSesion;

   @Column(name = "ES_ESTADO")
   private Integer estado;

   @Column(name = "ES_ELIMINADO")
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
