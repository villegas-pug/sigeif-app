package microservice.educalle.contacto.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.shared_data.entities.DocumentoEntity;
import microservice.shared_data.entities.PaisEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactoResponseDto {

   private Long idContacto;
   // private Aliado aliado;
   private DocumentoEntity tipoDoc;
   private PaisEntity nacionalidad;
   private String numeroDoc;
   private String nombres;
   private String primerApe;
   private String segundoApe;
   private String telefono;
   private String direccion;
   private String correo;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

}
