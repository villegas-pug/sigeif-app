package microservice.sigesu.centroreferencia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.ubigeo.model.UbigeoNombre;
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
