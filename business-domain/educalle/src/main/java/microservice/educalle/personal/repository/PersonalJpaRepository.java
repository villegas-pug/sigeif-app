package microservice.educalle.personal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.PersonalEntity;

public interface PersonalJpaRepository extends JpaRepository<PersonalEntity, Long> {

}
