package microservice.shared_data.entities;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TGPERSONA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idPersona" })
public class PersonaEntity {

   @Id
   @Column(name = "IDPERSONA")
   private Long idPersona;

   @Column(name = "PERSEXO")
   private Integer sexo;

   @Column(name = "PERNOMBRE")
   private String nombres;

   @Column(name = "PERAPEPATERNO")
   private String apePaterno;

   @Column(name = "PERAPEMATERNO")
   private String apeMaterno;

   @OneToOne
   @JoinColumn(name = "PERDOCUMENTO")
   private DocumentoEntity tipoDoc;

   @Column(name = "PERNRODOCUMENTO")
   private String numeroDoc;

   @Column(name = "PERFECNACIMIENTO")
   private LocalDate fechaNacimiento;

   @Column(name = "PERDIRECCION")
   private String direccion;

   @Column(name = "PERTELEFONO")
   private String telefono;

   @Column(name = "PERCORREO")
   private String correo;

}
