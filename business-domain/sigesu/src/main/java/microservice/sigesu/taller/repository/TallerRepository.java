package microservice.sigesu.taller.repository;

import java.util.List;
import microservice.sigesu.taller.model.Taller;

public interface TallerRepository {

   Taller save(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

}
