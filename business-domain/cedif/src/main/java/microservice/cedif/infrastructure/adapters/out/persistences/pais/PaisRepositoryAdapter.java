package microservice.cedif.infrastructure.adapters.out.persistences.pais;

import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Pais;
import microservice.cedif.domain.ports.out.PaisRepositoryPort;
import microservice.shared_data.entities.PaisEntity;

@Repository
@AllArgsConstructor
public class PaisRepositoryAdapter implements PaisRepositoryPort {

   private final PaisJpaRepository repository;
   private final PaisEntityMapper mapper;

   @Override
   public List<Pais> findAllByNacionalidadIsNotNull() {
      List<PaisEntity> paises = this.repository.findByNacionalidadIsNotNull();
      return this.mapper.toModels(paises);
   }

}
