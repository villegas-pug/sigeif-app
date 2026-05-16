package microservice.sigesu.centros.dto;

import lombok.Data;

@Data
public class CentroDTO {

    private Long idUnidadOrganica;
    private String nombreUnidad;
    private String departamento;
    private String provincia;
    private String distrito;
    private String respDirector;
    private String tipoCentro;
}