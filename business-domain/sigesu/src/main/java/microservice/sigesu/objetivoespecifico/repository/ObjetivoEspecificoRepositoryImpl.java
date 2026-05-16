package microservice.sigesu.objetivoespecifico.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.sigesu.objetivoespecifico.mappers.ObjetivoEspecificoEntityMapper;
import microservice.sigesu.objetivoespecifico.models.ObjetivoEspecifico;

@Repository
@AllArgsConstructor
public class ObjetivoEspecificoRepositoryImpl implements ObjetivoEspecificoRepository {

   private final ObjetivoEspecificoJpaRepository jpaRepository;
   private final ObjetivoEspecificoEntityMapper mapper;

   @Override
   public List<ObjetivoEspecifico> findAllObjetivosEspecificos() {
      return this.jpaRepository.findAll().stream().map(this.mapper::toModel).toList();
   }

   @Override
   public Optional<ObjetivoEspecifico> findObjetivoById(Integer idObjetivo) {
      return this.jpaRepository.findById(idObjetivo).map(this.mapper::toModel);
   }

}
