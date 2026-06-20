package microservice.educalle.acta.repository;

import java.util.List;
import java.util.Optional;

import microservice.educalle.acta.model.Acta;

public interface ActaRepository {

   void saveActa(Acta acta);

   void deleteActaById(Long idActa);

   Optional<Acta> findActaById(Long id);

   List<Acta> findActasByIdAliado(Long idAliado);

}
