package microservice.educalle.aliado.repository;

import java.util.List;
import java.util.Optional;

import microservice.educalle.aliado.model.Aliado;

public interface AliadoRepository {

   Aliado saveAliado(Aliado aliado);

   Optional<Aliado> findAliadoById(Long idAliado);

   void deleteAliadoById(Long idAliado);

   List<Aliado> findAliadosByIdZona(Long idZona);

}
