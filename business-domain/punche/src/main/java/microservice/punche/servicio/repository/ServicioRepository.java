package microservice.punche.servicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.ServicioEntity;

@Repository
public interface ServicioRepository extends JpaRepository<ServicioEntity, Long> {

}
