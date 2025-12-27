package microservice.punche.unidadorganica.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import microservice.shared_data.entities.UnidadOrganicaEntity;

@Repository
public interface UnidadOrganicaRepository extends JpaRepository<UnidadOrganicaEntity, Long> {

   List<UnidadOrganicaEntity> findByNombreReferenciaContainingIgnoreCase(String ref);

}