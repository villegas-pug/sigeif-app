package microservice.educalle.objetivoespecifico.repository;

import java.util.List;
import java.util.Optional;

import microservice.educalle.objetivoespecifico.models.ObjetivoEspecifico;

public interface ObjetivoEspecificoRepository {

   List<ObjetivoEspecifico> findAllObjetivosEspecificos();

   Optional<ObjetivoEspecifico> findObjetivoById(Integer idObjetivo);

}
