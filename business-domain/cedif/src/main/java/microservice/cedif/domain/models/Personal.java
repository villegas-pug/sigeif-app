package microservice.cedif.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Personal {

   private Long idPersonal;
   private Long idPersona;
   private Persona persona;
   private String nombres;
   private String primerApe;
   private String segundoApe;
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
