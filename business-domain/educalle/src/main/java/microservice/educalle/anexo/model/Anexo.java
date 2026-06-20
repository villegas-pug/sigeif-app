package microservice.educalle.anexo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Anexo {

    private Long idAnexo;
    private String anxCodigo;
    private String anxNombre;
    private String anxDescripcion;
    private Long idUnidadOrganica;
    private String anxUnidadOrganica;
    private Long anxIdServicio;
    private String anxServicio;
    private Integer anxEstado;
    private String anxCodigo2;
}
