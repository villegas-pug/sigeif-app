package microservice.punche.taller.repository;

import java.util.List;
import microservice.punche.taller.model.Taller;

public interface TallerRepository {

   Taller save(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

   List<Taller> findAllTallers();

}
