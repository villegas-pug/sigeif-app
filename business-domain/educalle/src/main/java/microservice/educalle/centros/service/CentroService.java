package microservice.educalle.centros.service;

import microservice.educalle.centros.dto.CentroDTO;
import java.util.List;

public interface CentroService {

    List<CentroDTO> listarCentros(Long idUnidadPadre);

}