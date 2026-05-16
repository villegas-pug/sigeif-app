package microservice.sigesu.motivoreferecia.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.sigesu.motivoreferecia.mappers.MotivoReferenciaEntityMapper;
import microservice.sigesu.motivoreferecia.model.MotivoReferecia;
import microservice.shared_data.entities.MotivoReferenciaEntity;

@Repository
@AllArgsConstructor
public class MotivoReferenciaRepositoryImpl implements MotivoReferenciaRepository {

   private MotivoReferenciaJpaRepository repository;
   private MotivoReferenciaEntityMapper mapper;

   @Override
   public List<MotivoReferecia> findAllMotivosReferencia() {
      List<MotivoReferenciaEntity> entities = repository.findAll();
      return this.mapper.toModels(entities);
   }

}
