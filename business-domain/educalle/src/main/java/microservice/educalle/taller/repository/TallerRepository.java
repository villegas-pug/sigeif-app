package microservice.educalle.taller.repository;

import java.util.List;
import microservice.educalle.taller.model.Taller;

public interface TallerRepository {

   Taller save(Taller taller);

   List<Taller> findAllTallerByIdSesion(Integer idSesion);

}
