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
@Table(name = "TG_PAIS")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "idPais" })
public class PaisEntity {

   @Id
   @Column(name = "IDPAIS")
   private Long idPais;

   @Column(name = "PA_NOMBRE")
   private String nombre;

   @Column(name = "PA_NACIONALIDAD")
   private String nacionalidad;

}
