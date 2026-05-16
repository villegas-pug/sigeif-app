package microservice.sigesu.centros.repository;

import java.util.List;

import microservice.sigesu.centros.dto.CentroDTO;

public interface CentroRepository {

    List<CentroDTO> listarCentros(Long idUnidadPadre);

}