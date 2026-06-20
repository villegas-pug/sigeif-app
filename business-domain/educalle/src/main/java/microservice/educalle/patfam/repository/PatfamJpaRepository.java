package microservice.educalle.patfam.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.PatfamEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;

public interface PatfamJpaRepository extends JpaRepository<PatfamEntity, Long> {

   Optional<PatfamEntity> findTop1ByFamiliaOrderByIdPatfamDesc(PotencialFamiliaEntity familia);

}
