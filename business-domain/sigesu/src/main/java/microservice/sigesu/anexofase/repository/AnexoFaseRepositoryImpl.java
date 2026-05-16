package microservice.sigesu.anexofase.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.shared_data.enums.InabifServices;
import microservice.sigesu.anexofase.mappers.AnexoFaseEntityMapper;
import microservice.sigesu.anexofase.model.AnexoFase;

@Repository
@AllArgsConstructor
public class AnexoFaseRepositoryImpl implements AnexoFaseRepository {

   private final AnexoFaseJpaRepository jpaRepository;
   private final AnexoFaseEntityMapper mapper;

   @Override
   public List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo) {
      return this.jpaRepository.findByIdServicioAndNumAnexo(InabifServices.PUNCHE.getId().intValue(), numAnexo)
            .stream()
            .map(this.mapper::toModel)
            .toList();
   }

}
