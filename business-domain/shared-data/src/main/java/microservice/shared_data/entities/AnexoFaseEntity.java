package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_ANEXO_FASES")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idFase" })
public class AnexoFaseEntity {

   @Id
   @Column(name = "SF_ID_FASE")
   private Integer idFase;

   @Column(name = "SF_NOMBRE")
   private String nombre;

   @Column(name = "SF_ID_SERVICIO")
   private Integer idServicio;

   @Column(name = "SF_NUM_ANEXO")
   private Integer numAnexo;

   @Column(name = "SF_ORDEN")
   private Integer orden;

   @Column(name = "FF_ESTADO")
   private Integer estado;

   @Column(name = "FF_ELIMINADO")
   private Integer eliminado;

}
