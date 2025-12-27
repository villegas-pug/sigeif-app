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
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idCargo" })
public class Cargo {

   @Id
   @Column(name = "IDCATALOGO")
   private Long idCargo;

   @Column(name = "CATDESCRIPCION")
   private String nombre;

}
