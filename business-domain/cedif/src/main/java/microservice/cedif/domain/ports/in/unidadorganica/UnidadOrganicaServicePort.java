package microservice.cedif.domain.ports.in.unidadorganica;

import java.util.List;

import microservice.cedif.domain.models.UnidadOrganica;

public interface UnidadOrganicaServicePort {

   List<UnidadOrganica> findUnidadesOrganicasByNombreReferencia(String ref);

}
