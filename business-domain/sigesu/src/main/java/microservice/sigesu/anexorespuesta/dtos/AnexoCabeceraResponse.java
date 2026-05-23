package microservice.sigesu.anexorespuesta.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnexoCabeceraResponse {

    private Long idAnexoCabecera;
    private Long idAnexo;
    private Long idCentro;
    private Integer correlativo;
    private Integer usuRegistra;
    private Integer estado;
    private Integer eliminado;

    private String nombreAnexo;
    private String codigoAnexo2;
    private String nombreUnidad;
    private String nombreServicio;
    private String nombreCentro;
    private String respDirector;
    private Long idDirector;
    private String tipoCentro;
    private Long idRespSupervision;
    private Long idSupervisado;
    private Integer reqValidacion;

    private String periodo;
    private String tipo;
}