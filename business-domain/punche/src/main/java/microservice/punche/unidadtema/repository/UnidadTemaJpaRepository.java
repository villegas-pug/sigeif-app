package microservice.punche.unidadtema.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import microservice.shared_data.entities.UnidadTemaEntity;
import microservice.shared_data.entities.UnidadEntity;

public interface UnidadTemaJpaRepository extends JpaRepository<UnidadTemaEntity, Integer> {

   Set<UnidadTemaEntity> findByUnidad(UnidadEntity unidad);
   

}
