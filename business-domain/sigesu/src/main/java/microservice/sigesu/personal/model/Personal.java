package microservice.sigesu.personal.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.persona.model.Persona;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personal {

   private Long idPersona;
   private Long idPersonal;

   private Persona persona;

   private String nombres;
   private String documento;
   private String numeroDoc;
   private String nacionalidad;
   private String estadoCivil;
   private String sexo;
   private LocalDate fecNacimiento;
   private String telefono;
   private String correo;
   private String direccion;
   private String referencia;
   private String carrera;

}
