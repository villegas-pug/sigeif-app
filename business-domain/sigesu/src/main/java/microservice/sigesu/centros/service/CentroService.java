package microservice.sigesu.centros.service;

import microservice.sigesu.centros.dto.CentroDTO;
import java.util.List;

public interface CentroService {

    List<CentroDTO> listarCentros(Long idUnidadPadre);

}