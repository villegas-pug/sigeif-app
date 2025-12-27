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
@Table(name = "SSI_MOTIVO_REFERENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idMotivo" })
public class MotivoReferenciaEntity {

   @Id
   @Column(name = "MR_ID_MOTIVO")
   private Integer idMotivo;

   @Column(name = "MR_DESCRIPCION")
   private String descripcion;

   @Column(name = "MR_ESTADO")
   private Integer estado;

}
