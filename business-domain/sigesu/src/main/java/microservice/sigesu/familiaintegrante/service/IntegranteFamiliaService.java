package microservice.sigesu.familiaintegrante.service;

import java.util.List;
import microservice.sigesu.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.sigesu.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;

public interface IntegranteFamiliaService {

   <M> M createIntegranteFamilia(CreateFamiliaIntegranteRequest integranteFamilia);

   <M> M updateIntegranteFamilia(UpdateFamiliaIntegranteRequest integranteFamilia);

   <M> List<M> updateIntegrantesFamilia(List<UpdateFamiliaIntegranteRequest> integrantesFamilia);

   <M> M findFamiliaIntegranteById(Long idIntegrante);

   <M> M deleteFamiliaIntegranteById(Long idIntegrante);

}
