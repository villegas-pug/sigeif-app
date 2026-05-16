package microservice.punche.unidadtema.service;

import java.util.List;

import microservice.punche.unidadtema.model.UnidadTema;

public interface UnidadTemaService {

   UnidadTema createUnidadTema(UnidadTema sesion);

   UnidadTema updateUnidadTema(UnidadTema sesion);

   List<UnidadTema> findAllTemaByIdUnidad(Integer idUnidad);

}
