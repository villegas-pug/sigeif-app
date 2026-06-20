package microservice.educalle.unidadsesion.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.entities.UnidadEntity;

public interface UnidadSesionJpaRepository extends JpaRepository<UnidadSesionEntity, Integer> {

   Set<UnidadSesionEntity> findByUnidad(UnidadEntity unidad);

}
