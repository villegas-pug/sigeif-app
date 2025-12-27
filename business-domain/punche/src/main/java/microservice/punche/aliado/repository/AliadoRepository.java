package microservice.punche.aliado.repository;

import java.util.List;
import java.util.Optional;
import microservice.punche.aliado.model.Aliado;

public interface AliadoRepository {

   Aliado saveAliado(Aliado aliado);

   Optional<Aliado> findAliadoById(Long idAliado);

   void deleteAliadoById(Long idAliado);

   List<Aliado> findAliadosByIdZona(Long idZona);

}
