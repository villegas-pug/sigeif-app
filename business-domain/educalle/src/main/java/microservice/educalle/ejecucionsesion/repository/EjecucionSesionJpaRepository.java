package microservice.educalle.ejecucionsesion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.EjecucionSesionEntity;

public interface EjecucionSesionJpaRepository extends JpaRepository<EjecucionSesionEntity, Long> {

}
