package microservice.cedif.domain.ports.out;

import java.util.List;
import microservice.cedif.domain.models.UnidadOrganica;

public interface UnidadOrganicaRepositoryPort {

   List<UnidadOrganica> findUnidadesOrganicasByNombreReferencia(String ref);

   List<UnidadOrganica> findUnidadesOrganicasByParams(Long idServicio, Integer anio, Integer mes, String ref);

   List<UnidadOrganica> findAllUnidadOrganicas();

}