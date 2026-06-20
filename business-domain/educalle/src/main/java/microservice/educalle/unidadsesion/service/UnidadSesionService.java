package microservice.educalle.unidadsesion.service;

import java.util.List;

import microservice.educalle.unidadsesion.model.UnidadSesion;

public interface UnidadSesionService {

   UnidadSesion createUnidadSesion(UnidadSesion sesion);

   UnidadSesion updateUnidadSesion(UnidadSesion sesion);

   List<UnidadSesion> findAllSesionByIdUnidad(Integer idUnidad);

}
