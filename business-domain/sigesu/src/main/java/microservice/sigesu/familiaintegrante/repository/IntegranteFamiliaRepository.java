package microservice.sigesu.familiaintegrante.repository;

import java.util.List;

import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;

public interface IntegranteFamiliaRepository {

   <M> M save(FamiliaIntegrante integrantesFamilia);

   <M> List<M> saveAll(List<FamiliaIntegrante> integrantesFamilia);

   <M> M findFamiliaIntegranteById(Long idIntegrante);

   <M> M deleteFamiliaIntegranteById(Long idIntegrante);

}
