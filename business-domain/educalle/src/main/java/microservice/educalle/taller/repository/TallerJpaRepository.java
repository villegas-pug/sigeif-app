package microservice.educalle.taller.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadSesionEntity;

public interface TallerJpaRepository extends JpaRepository<TallerEntity, Integer> {

   List<TallerEntity> findBySesion(UnidadSesionEntity sesion);

}
