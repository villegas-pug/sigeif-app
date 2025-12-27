package microservice.shared_data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TGINSTITUCION")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idInstitucion" })
public class InstitucionEntity {

   @Id
   @Column(name = "IDINSTITUCION")
   private Long idInstitucion;

   @Column(name = "INSNOMBRE")
   private String nombreReferencia;

   @Column(name = "INSDIRECCION")
   private String direccion;

   @Column(name = "INSREFERENCIA")
   private String referencia;

   @Column(name = "INSTELEFONO1")
   private String telefono;

   @Column(name = "INSCORREO")
   private String correo;

   @Column(name = "INSREPRESENTANTE")
   private String representante; // INSREPRESENTANTE

   @Column(name = "INSRUC")
   private String ruc; // INSRUC

   @Column(name = "INSCONTACTO")
   private String contacto; // INSCONTACTO

   /*
    * @Column(name = "INSUBIGEO")
    * private String ubigeo; // INSCONTACTO
    */

   @OneToOne
   @JoinColumn(name = "INSUBIGEO")
   private UbigeoNombreEntity ubigeo;

}