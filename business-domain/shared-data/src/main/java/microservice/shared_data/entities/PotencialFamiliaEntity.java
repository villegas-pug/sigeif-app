package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "SSI_POTENCIALES_FAMILIAS")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idFamilia" })
public class PotencialFamiliaEntity {

   @Id
   @Column(name = "PF_ID_FAMILIA")
   private Long idFamilia;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "familia")
   @JsonIgnoreProperties(value = { "familia" })
   private List<CodigoFamiliaEntity> codigoFamilia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "ZO_ID_ZONA")
   @JsonIgnoreProperties(value = { "potencialesFamilias" })
   private ZonaIntervencionEntity zonaIntervencion;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "AL_ID_ALIADO")
   @JsonIgnoreProperties(value = { "zonaIntervencion" })
   private AliadoEntity aliado;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "UO_ID_UNIDADORGANICA")
   @JsonIgnoreProperties(value = { "potencialesFamilias" })
   private UnidadOrganicaEntity unidadOrganica;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "SI_ID_SERVICIO")
   private ServicioEntity servicio;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "PER_ID_PERSONAL")
   private PersonalEntity acompañante;

   @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "familia" })
   private Set<AnexoRespuestaEntity> anexosRespuestas;

   @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "familia" })
   private Set<IntegranteFamiliaEntity> integrantesFamilia;

   @OneToMany(mappedBy = "familia", fetch = FetchType.LAZY)
   @JsonIgnoreProperties(value = { "familia" })
   private Set<FamiliaMotivoReferenciaEntity> motivosReferencia;

   @Column(name = "PF_COD_FAMILIA")
   private String codFamilia;

   @Column(name = "PF_OBSERVACIONES")
   private String observaciones;

   @Column(name = "PF_FAMILIA_APTA")
   private Integer familiaApta;

   @Column(name = "PF_USU_REGISTRA")
   private Integer usuRegistra;

   @Column(name = "PF_FEC_REGISTRA")
   private LocalDate fecRegistra;

   @Column(name = "PF_USU_ACTUALIZA")
   private Integer usuActualiza;

   @Column(name = "PF_FEC_ACTUALIZA")
   private LocalDate fecActualiza;

   @Column(name = "PF_ESTADO")
   private Integer estado;

   @Column(name = "PF_ELIMINADO")
   private Integer eliminado;

   @PrePersist
   private void prePersist() {
      this.setFecRegistra(LocalDate.now());
      this.setEstado(1);
      this.setEliminado(0);
      this.setFamiliaApta(0);
   }

   @PreUpdate
   private void preUpdate() {
      this.setFecActualiza(LocalDate.now());
   }

   public String getCodFamilia() {
      if (this.codigoFamilia == null || this.codigoFamilia.isEmpty()) {
         return null;
      }
      return this.codigoFamilia
            .stream()
            .max(Comparator.comparing(CodigoFamiliaEntity::getTipoCodigo))
            .map(CodigoFamiliaEntity::getCodigo)
            .orElse(null);
   }

}
