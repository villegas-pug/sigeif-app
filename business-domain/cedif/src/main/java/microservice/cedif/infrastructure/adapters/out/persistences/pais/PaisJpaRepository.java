package microservice.cedif.infrastructure.adapters.out.persistences.pais;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.PaisEntity;

@Repository
public interface PaisJpaRepository extends JpaRepository<PaisEntity, Integer> {

   List<PaisEntity> findByNacionalidadIsNotNull();

}