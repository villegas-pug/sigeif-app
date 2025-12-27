package microservice.shared_data.entities;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@MappedSuperclass
public class DivisionTerritorialEntity {
   @Transient
   private String idDepartamento = "-";

   @Transient
   private String departamento = "-";

   @Transient
   private String idProvincia = "-";

   @Transient
   private String provincia = "-";

   @Transient
   private String idDistrito = "-";

   @Transient
   private String distrito = "-";
}
