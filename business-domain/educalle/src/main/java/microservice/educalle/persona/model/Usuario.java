package microservice.educalle.persona.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

   private Long idUsuario;
   private String login;
   private Persona persona;
   private Integer estado;

}
