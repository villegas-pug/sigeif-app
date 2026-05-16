package microservice.sigesu.persona.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.PersonaEntity;

public interface PersonaJpaRepository extends JpaRepository<PersonaEntity, Long> {

}
