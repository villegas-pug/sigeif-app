package microservice.cedif.infrastructure.adapters.out.persistences.institucion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.InstitucionEntity;
import java.util.List;

@Repository
public interface InstitucionJpaRepository extends JpaRepository<InstitucionEntity, Long> {

   List<InstitucionEntity> findByNombreReferenciaContainingIgnoreCase(String ref);

}
