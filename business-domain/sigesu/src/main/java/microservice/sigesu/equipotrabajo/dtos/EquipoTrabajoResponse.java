package microservice.sigesu.equipotrabajo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.documento.model.Documento;
import microservice.shared_data.entities.Cargo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoTrabajoResponse {

   private Long idEquipo;
   private Cargo cargo;
   private Integer eliminado;

   // * Persona
   private Long idPersonal;
   private Long idPersona;
   private String sexo;
   private String nombres;
   private String apePaterno;
   private String apeMaterno;
   private Documento tipoDoc;
   private String numeroDoc;
   private LocalDate fechaNacimiento;
   private String direccion;
   private String telefono;
   private String correo;

   public String getSexo() {
      return this.sexo.equals(1) ? "Masculino" : "Femenino";
   }

}
