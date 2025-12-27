package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_FAMILIA_MOTIVO_REFERENCIA")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FamiliaMotivoReferenciaEntity {

   @Id
   @Column(name = "FMR_ID_FMR")
   private Long idFmr;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "PF_ID_FAMILIA")
   private PotencialFamiliaEntity familia;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "MR_ID_MOTIVO")
   private MotivoReferenciaEntity motivo;

   @Column(name = "FMR_ESTADO")
   private Integer estado;

   @Column(name = "FMR_ELIMINADO")
   private Integer eliminado;

}
