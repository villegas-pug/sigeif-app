package microservice.cedif.infrastructure.adapters.out.persistences.institucion;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Institucion;
import microservice.cedif.domain.ports.out.InstitucionRepositoryPort;
import microservice.shared_data.entities.InstitucionEntity;

@Repository
@AllArgsConstructor
public class InstitucionRepositoryAdapter implements InstitucionRepositoryPort {

   private final InstitucionJpaRepository repository;
   private final InstitucionEntityMapper mapper;

   @Override
   public List<Institucion> findTop10ByNombreReferenciaContainingIgnoreCase(String ref) {
      List<InstitucionEntity> instituciones = this.repository.findByNombreReferenciaContainingIgnoreCase(ref);
      return this.mapper.toModels(instituciones);
   }

   @Override
   public List<Institucion> findAllInstituciones() {
      List<InstitucionEntity> instituciones = this.repository.findAll();
      return this.mapper.toModels(instituciones);
   }
}
