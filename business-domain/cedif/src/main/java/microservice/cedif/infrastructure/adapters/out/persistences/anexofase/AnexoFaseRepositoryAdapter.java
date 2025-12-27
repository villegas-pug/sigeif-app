package microservice.cedif.infrastructure.adapters.out.persistences.anexofase;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoFase;
import microservice.cedif.domain.ports.out.AnexoFaseRepositoryPort;
import microservice.shared_data.enums.InabifServices;

@Repository
@AllArgsConstructor
public class AnexoFaseRepositoryAdapter implements AnexoFaseRepositoryPort {

   private final AnexoFaseJpaRepository jpaRepository;
   private final AnexoFaseEntityMapper mapper;

   @Override
   public List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo) {
      return this.jpaRepository.findByIdServicioAndNumAnexo(InabifServices.CEDIF.getId().intValue(), numAnexo)
            .stream()
            .map(this.mapper::toModel)
            .toList();
   }

}
