package microservice.educalle.acta.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.AllArgsConstructor;
import microservice.shared_data.entities.ActaEntity;
import microservice.shared_data.entities.AliadoEntity;
import microservice.educalle.acta.mappers.ActaEntityMapper;
import microservice.educalle.acta.model.Acta;

@Repository
@AllArgsConstructor
public class ActaRepositoryImpl implements ActaRepository {

   private final ActaJpaRepository jpaRepository;
   private final ActaEntityMapper mapper;

   @Override
   public void saveActa(Acta acta) {
      ActaEntity createActa = new ActaEntity();

      if (acta.getIdActa() != null) { // * Actualiza
         createActa = this.jpaRepository.findById(acta.getIdActa()).get();
      }

      this.mapper.fromModelToEntity(acta, createActa);
      this.jpaRepository.save(createActa);
   }

   @Override
   public Optional<Acta> findActaById(Long id) {
      return this.jpaRepository.findById(id).map(this.mapper::toModel);
   }

   @Override
   public void deleteActaById(Long idActa) {
      this.jpaRepository.findById(idActa).ifPresent(acta -> {
         acta.setEliminado(1); // ! Eliminado
         this.jpaRepository.save(acta);
      });
   }

   @Override
   public List<Acta> findActasByIdAliado(Long idAliado) {
      return this.jpaRepository.findByAliado(AliadoEntity.builder().idAliado(idAliado).build())
            .stream()
            .map(this.mapper::toModel)
            .toList();
   }

}
