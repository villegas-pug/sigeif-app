package microservice.educalle.centros.repository;

import java.util.List;

import microservice.educalle.centros.dto.CentroDTO;

public interface CentroRepository {

    List<CentroDTO> listarCentros(Long idUnidadPadre);

}