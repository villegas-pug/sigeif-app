package microservice.educalle.familiaintegrante.service;

import java.util.List;
import microservice.educalle.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.educalle.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;

public interface IntegranteFamiliaService {

   <M> M createIntegranteFamilia(CreateFamiliaIntegranteRequest integranteFamilia);

   <M> M updateIntegranteFamilia(UpdateFamiliaIntegranteRequest integranteFamilia);

   <M> List<M> updateIntegrantesFamilia(List<UpdateFamiliaIntegranteRequest> integrantesFamilia);

   <M> M findFamiliaIntegranteById(Long idIntegrante);

   <M> M deleteFamiliaIntegranteById(Long idIntegrante);

}
