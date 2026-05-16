package microservice.sigesu.anexofase.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.AnexoFaseEntity;

public interface AnexoFaseJpaRepository extends JpaRepository<AnexoFaseEntity, Integer> {

   List<AnexoFaseEntity> findByIdServicioAndNumAnexo(Integer idServicio, Integer numAnexo);

}
