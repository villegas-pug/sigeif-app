package microservice.cedif.domain.ports.out;

import java.util.List;
import microservice.cedif.domain.models.Pais;

public interface PaisRepositoryPort {

   List<Pais> findAllByNacionalidadIsNotNull();

}
