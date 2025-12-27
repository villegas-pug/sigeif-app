package microservice.cedif.domain.ports.out;

import java.util.List;
import microservice.cedif.domain.models.Personal;

public interface PersonalRepositoryPort {

   public List<Personal> findPersonalByDocumento(String nroDoc);

   public List<Personal> findPersonalByParams(String nroDoc, String nombres);

}
