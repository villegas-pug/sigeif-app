package microservice.shared_data.entities;

import java.time.LocalDate;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "SSI_ZONA_INTERVENCION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idZona" })
public class ZonaIntervencionEntity {

    @Id
    @Column(name = "ZO_ID_ZONA")
    private Long idZona;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SI_ID_SERVICIO")
    private ServicioEntity servicio;

    @OneToMany(mappedBy = "zonaIntervencion", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({ "zonaIntervencion" })
    private Set<EquipoTrabajoEntity> equiposTrabajo;

    @OneToMany(mappedBy = "zonaIntervencion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({ "zonaIntervencion" })
    private Set<AliadoEntity> aliados;

    // ! Recursion: aliado
    @OneToMany(mappedBy = "zonaIntervencion", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "zonaIntervencion", "unidadOrganica", "servicio", "anexosRespuestas", "aliado" })
    private Set<PotencialFamiliaEntity> potencialesFamilias;

    @OneToOne
    @JoinColumn(name = "INS_ID_INSTITUCION")
    private InstitucionEntity institucion;

    @OneToOne
    @JoinColumn(name = "UOR_ID_UNIDADORG")
    @JsonIgnoreProperties(value = { "potencialesFamilias" })
    private UnidadOrganicaEntity unidadOrganica;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UBI_ID_UBIGEO")
    private UbigeoNombreEntity ubigeo;

    @Column(name = "ZO_COD_TIPO")
    private Integer codTipo;

    @Column(name = "ZO_DESCRIPCION")
    private String descripcion;

    @Column(name = "ZO_USU_REGISTRA")
    private Integer usuRegistra;

    @Column(name = "ZO_FEC_REGISTRA")
    private LocalDate fecRegistra;

    @Column(name = "ZO_USU_ACTUALIZA")
    private Integer usuActualiza;

    @Column(name = "ZO_FEC_ACTUALIZA")
    private LocalDate fecActualiza;

    @Column(name = "ZO_ESTADO")
    private Integer estado;

    @Column(name = "ZO_ELIMINADO")
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
