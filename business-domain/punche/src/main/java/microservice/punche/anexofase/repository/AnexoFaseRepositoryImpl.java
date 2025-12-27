package microservice.punche.anexofase.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.punche.anexofase.mappers.AnexoFaseEntityMapper;
import microservice.punche.anexofase.model.AnexoFase;
import microservice.shared_data.enums.InabifServices;

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
