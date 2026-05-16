package microservice.sigesu.unidadsesion.service;

import java.util.List;

import microservice.sigesu.unidadsesion.model.UnidadSesion;

public interface UnidadSesionService {

   UnidadSesion createUnidadSesion(UnidadSesion sesion);

   UnidadSesion updateUnidadSesion(UnidadSesion sesion);

   List<UnidadSesion> findAllSesionByIdUnidad(Integer idUnidad);

}
