package microservice.sigesu.objetivoespecifico.repository;

import java.util.List;
import java.util.Optional;

import microservice.sigesu.objetivoespecifico.models.ObjetivoEspecifico;

public interface ObjetivoEspecificoRepository {

   List<ObjetivoEspecifico> findAllObjetivosEspecificos();

   Optional<ObjetivoEspecifico> findObjetivoById(Integer idObjetivo);

}
