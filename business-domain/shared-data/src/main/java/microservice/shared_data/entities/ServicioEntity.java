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
@Table(name = "SSI_SERVICIOS_INABIF")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idServicio" })
public class ServicioEntity {

   @Id
   @Column(name = "SI_ID_SERVICIO")
   private Long idServicio;

   @Column(name = "SI_NOMBRE")
   private String nombre;

}
