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
@Table(name = "TGCATALOGO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = { "idTipoDoc" })
public class DocumentoEntity {

   @Id
   @Column(name = "IDCATALOGO")
   private Long idTipoDoc;

   @Column(name = "CATDESCRIPCION")
   private String tipoDoc;

}
