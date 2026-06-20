package microservice.educalle.acta.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.ActaEntity;
import microservice.shared_data.entities.AliadoEntity;

public interface ActaJpaRepository extends JpaRepository<ActaEntity, Long> {

   List<ActaEntity> findByAliado(AliadoEntity aliado);

}
