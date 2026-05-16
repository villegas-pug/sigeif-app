package microservice.sigesu.anexo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoDto {

private Long idAnexo;
private String nombreAnexo;
private Long idServicio;
private String nombreServicio;
private Long idUnidad;
private String nombreUnidad;
private String respDirector;
private String codigoAnexo2;
}
