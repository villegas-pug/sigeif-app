package microservice.punche.programaciontallerfamilia.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.ProgramacionTallerFamiliaEntity;

public interface ProgramacionTallerFamiliaJpaRepositiry extends JpaRepository<ProgramacionTallerFamiliaEntity, Long> {

}
