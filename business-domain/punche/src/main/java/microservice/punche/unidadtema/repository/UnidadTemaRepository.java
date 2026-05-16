package microservice.punche.unidadtema.repository;

import java.util.List;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.shared_data.entities.UnidadEntity;

public interface UnidadTemaRepository {

   UnidadTema save(UnidadTema sesion);

   List<UnidadTema> findAllTemaByIdUnidad(Integer idUnidad);

}
