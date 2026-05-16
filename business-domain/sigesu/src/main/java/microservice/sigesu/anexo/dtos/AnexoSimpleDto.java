package microservice.sigesu.anexo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoSimpleDto {

    private Long idAnexo;
    private String nombreAnexo;
    private String codigoAnexo2; 
}
