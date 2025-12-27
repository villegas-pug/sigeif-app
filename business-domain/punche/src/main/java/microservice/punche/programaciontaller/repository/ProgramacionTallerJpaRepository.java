package microservice.punche.programaciontaller.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.ProgramacionTallerEntity;

public interface ProgramacionTallerJpaRepository extends JpaRepository<ProgramacionTallerEntity, Long> {

}
