package microservice.cedif.domain.ports.in.pais;

import java.util.List;
import microservice.cedif.domain.models.Pais;

public interface PaisServicePort {

   List<Pais> findAllPais();

}