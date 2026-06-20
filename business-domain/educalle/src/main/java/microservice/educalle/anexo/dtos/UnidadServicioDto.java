package microservice.educalle.anexo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadServicioDto {

    private Long idUnidad;
    private String nombreUnidad;

    private Long idServicio;
    private String nombreServicio;
}
