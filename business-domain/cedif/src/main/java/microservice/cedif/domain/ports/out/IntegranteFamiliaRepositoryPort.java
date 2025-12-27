package microservice.cedif.domain.ports.out;

import java.util.List;
import java.util.Optional;

import microservice.cedif.domain.models.FamiliaIntegrante;

public interface IntegranteFamiliaRepositoryPort {

   FamiliaIntegrante save(FamiliaIntegrante integrantesFamilia);

   <M> List<M> saveAll(List<FamiliaIntegrante> integrantesFamilia);

   Optional<FamiliaIntegrante> findFamiliaIntegranteById(Long idIntegrante);

   <M> M deleteFamiliaIntegranteById(Long idIntegrante);

}
