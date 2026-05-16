package microservice.sigesu.familiaintegrante.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.IntegranteFamiliaEntity;

public interface IntegranteFamiliaJpaRepository extends JpaRepository<IntegranteFamiliaEntity, Long> {

}
