package microservice.cedif.infrastructure.adapters.out.persistences.servicio;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.ServicioEntity;

public interface ServicioJpaRepository extends JpaRepository<ServicioEntity, Long> {

}
