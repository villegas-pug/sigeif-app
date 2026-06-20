package microservice.educalle.pais.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.PaisEntity;

@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Integer> {

   List<PaisEntity> findByNacionalidadIsNotNull();

}