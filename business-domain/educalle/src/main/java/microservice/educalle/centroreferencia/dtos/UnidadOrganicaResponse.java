package microservice.educalle.centroreferencia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.personal.model.Personal;
import microservice.educalle.ubigeo.model.UbigeoNombre;
import microservice.shared_data.entities.DivisionTerritorialEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "idUO")
public class UnidadOrganicaResponse extends DivisionTerritorialEntity {

   private Long idUO;
   private String nombreReferencia;
   private String telefono;
   private String direccion;
   private String referencia;
   private String correo;
   private UbigeoNombre ubigeo; // INSCONTACTO
   private Personal representante;

}
