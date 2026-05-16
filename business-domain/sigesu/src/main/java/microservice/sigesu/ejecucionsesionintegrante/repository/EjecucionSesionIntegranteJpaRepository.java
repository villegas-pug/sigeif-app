package microservice.sigesu.ejecucionsesionintegrante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.EjecucionSesionIntegranteEntity;

public interface EjecucionSesionIntegranteJpaRepository extends JpaRepository<EjecucionSesionIntegranteEntity, Long> {

}
