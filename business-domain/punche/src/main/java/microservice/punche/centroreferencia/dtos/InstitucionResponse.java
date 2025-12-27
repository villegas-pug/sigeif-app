package microservice.punche.centroreferencia.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.shared_data.entities.DivisionTerritorialEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, of = "idInstitucion")
public class InstitucionResponse extends DivisionTerritorialEntity {

   private Long idInstitucion;
   private String nombreReferencia;
   private String direccion;
   private String referencia;
   private String telefono;
   private String correo;
   private String representante; // INSREPRESENTANTE
   private String ruc; // INSRUC
   private String contacto; // INSCONTACTO
   private String ubigeo; // INSCONTACTO

}
