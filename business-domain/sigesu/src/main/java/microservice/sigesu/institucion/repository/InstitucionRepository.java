package microservice.sigesu.institucion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservice.shared_data.entities.InstitucionEntity;
import java.util.List;

@Repository
public interface InstitucionRepository extends JpaRepository<InstitucionEntity, Long> {

   List<InstitucionEntity> findByNombreReferenciaContainingIgnoreCase(String ref);

}
