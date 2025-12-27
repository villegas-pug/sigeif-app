package microservice.punche.unidadsesion.repository;

import java.util.List;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.UnidadEntity;

public interface UnidadSesionRepository {

   UnidadSesion save(UnidadSesion sesion);

   List<UnidadSesion> findAllSesionByIdUnidad(Integer idUnidad);

}
