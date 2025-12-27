package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SSI_UBIGEO_NOMBRES")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbigeoNombreEntity {

   @Id
   @Column(name = "U_ID_UBIGEO")
   private String idUbigeo;

   @Column(name = "U_ID_DEPARTAMENTO")
   private String idDepartamento;

   @Column(name = "U_DEPARTAMENTO")
   private String departamento;

   @Column(name = "U_ID_PROVINCIA")
   private String idProvincia;

   @Column(name = "U_PROVINCIA")
   private String provincia;

   @Column(name = "U_ID_DISTRITO")
   private String idDistrito;

   @Column(name = "U_DISTRITO")
   private String distrito;

}
