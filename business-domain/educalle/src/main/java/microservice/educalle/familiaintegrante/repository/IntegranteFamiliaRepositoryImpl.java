package microservice.educalle.familiaintegrante.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.educalle.familiaintegrante.mappers.IntegranteFamiliaEntityMapper;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.entities.IntegranteFamiliaEntity;

@Repository
@AllArgsConstructor
public class IntegranteFamiliaRepositoryImpl implements IntegranteFamiliaRepository {

   private final IntegranteFamiliaJpaRepository jpaRepository;
   private final IntegranteFamiliaEntityMapper mapper;

   @Override
   public <M> List<M> saveAll(List<FamiliaIntegrante> integrantesFamilia) {
      return (List<M>) integrantesFamilia
            .stream()
            .map(this::save).toList();
   }

   @Override
   public <M> M deleteFamiliaIntegranteById(Long idIntegrante) {

      IntegranteFamiliaEntity entity = this.jpaRepository.findById(idIntegrante).map(integrante -> {
         integrante.setEliminado(1);
         return integrante;
      }).get();

      return (M) this.mapper.toModel(entity);
   }

   @Override
   public <M> M save(FamiliaIntegrante integranteModel) {

      IntegranteFamiliaEntity integranteEntity;

      // * New
      if (integranteModel.getIdIntegrante() == null) {
         integranteEntity = new IntegranteFamiliaEntity();
      } else { // * Update
         integranteEntity = this.jpaRepository
               .findById(integranteModel.getIdIntegrante())
               .get();

      }

      this.mapper.fromModelToEntity(integranteModel, integranteEntity);
      this.jpaRepository.save(integranteEntity);

      return (M) this.mapper.toModel(integranteEntity);

   }

   @Override
   public <M> M findFamiliaIntegranteById(Long idIntegrante) {
      return (M) this.jpaRepository.findById(idIntegrante).map(this.mapper::toModel).get();
   }

}
