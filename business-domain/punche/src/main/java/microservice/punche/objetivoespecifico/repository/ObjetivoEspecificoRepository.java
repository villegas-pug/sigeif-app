package microservice.punche.objetivoespecifico.repository;

import java.util.List;
import java.util.Optional;

import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;

public interface ObjetivoEspecificoRepository {

   List<ObjetivoEspecifico> findAllObjetivosEspecificos();

   Optional<ObjetivoEspecifico> findObjetivoById(Integer idObjetivo);

}
