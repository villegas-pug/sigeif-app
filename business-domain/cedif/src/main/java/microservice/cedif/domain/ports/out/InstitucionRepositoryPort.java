package microservice.cedif.domain.ports.out;

import org.springframework.stereotype.Repository;
import microservice.cedif.domain.models.Institucion;
import java.util.List;

@Repository
public interface InstitucionRepositoryPort {

   List<Institucion> findTop10ByNombreReferenciaContainingIgnoreCase(String ref);

   List<Institucion> findAllInstituciones();

}
