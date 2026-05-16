package microservice.sigesu.persona.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.documento.model.Documento;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

   private Long idPersona;
   private Integer sexo;
   private String nombres;
   private String apePaterno;
   private String apeMaterno;
   private Documento tipoDoc;
   private String numeroDoc;
   private LocalDate fechaNacimiento;
   private String direccion;
   private String telefono;
   private String correo;

   public String getNombresCompletos() {
      return this.nombres + ", " + this.apePaterno + " " + this.apeMaterno;
   }

   public String getSexo() {
      return this.sexo.equals(1) ? "Masculino" : "Femenino";
   }

}
