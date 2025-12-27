package microservice.cedif.infrastructure.adapters.out.persistences.integrantefamilia;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.ports.out.IntegranteFamiliaRepositoryPort;
import microservice.shared_data.entities.IntegranteFamiliaEntity;

@Repository
@AllArgsConstructor
public class IntegranteFamiliaRepositoryAdapter implements IntegranteFamiliaRepositoryPort {

   private final IntegranteFamiliaJpaRepository jpaRepository;
   private final IntegranteFamiliaEntityMapper mapper;

   @Override
   public <M> List<M> saveAll(List<FamiliaIntegrante> integrantesFamilia) {
      return (List<M>) integrantesFamilia
            .stream()
            .map(this::save)
            .toList();
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
   public FamiliaIntegrante save(FamiliaIntegrante integranteModel) {

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
      IntegranteFamiliaEntity newIntegranteEntity = this.jpaRepository.save(integranteEntity);

      return this.mapper.toModel(newIntegranteEntity);

   }

   @Override
   public Optional<FamiliaIntegrante> findFamiliaIntegranteById(Long idIntegrante) {
      return this.jpaRepository.findById(idIntegrante).map(this.mapper::toModel);
   }

}
