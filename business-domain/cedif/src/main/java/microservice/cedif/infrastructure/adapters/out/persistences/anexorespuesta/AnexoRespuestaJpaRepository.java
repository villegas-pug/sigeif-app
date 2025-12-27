package microservice.cedif.infrastructure.adapters.out.persistences.anexorespuesta;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.AnexoRespuestaEntity;

public interface AnexoRespuestaJpaRepository extends JpaRepository<AnexoRespuestaEntity, Long> {

}
