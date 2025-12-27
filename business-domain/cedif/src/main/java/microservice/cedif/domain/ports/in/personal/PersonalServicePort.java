package microservice.cedif.domain.ports.in.personal;

import java.util.List;
import microservice.cedif.domain.models.Personal;

public interface PersonalServicePort {

   public List<Personal> findPersonalByDocumento(String nroDoc);

   public List<Personal> findPersonalByParams(String nroDoc, String nombres);

}
