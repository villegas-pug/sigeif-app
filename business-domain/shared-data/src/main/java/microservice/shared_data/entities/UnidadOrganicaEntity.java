package microservice.shared_data.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TGUNIDADORGANICA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idUO" })
public class UnidadOrganicaEntity {

    @Id
    @Column(name = "IDUNIDADORGANICA")
    private Long idUO;

    @OneToMany(mappedBy = "unidadOrganica", fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "unidadOrganica", "zonaIntervencion", "aliado", "unidadOrganica" })
    private Set<PotencialFamiliaEntity> potencialesFamilias;

    @Column(name = "UORNOMBRE")
    private String nombreReferencia;

    @Column(name = "UORTELEFONO")
    private String telefono;

    @Column(name = "UORDIRECCION")
    private String direccion;

    @Column(name = "UOR_REFERENCIA")
    private String referencia;

    @Column(name = "UOR_CORREO_ELECTRONICO")
    private String correo;

    @Column(name = "UORABREVIATURA")
    private String nombreAbrev;

    @OneToOne
    @JoinColumn(name = "UOR_UBIGEO")
    private UbigeoNombreEntity ubigeo; // INSCONTACTO

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UOR_DIRECTOR")
    private PersonalEntity representante;
    // ! private PersonaEntity representante;

}