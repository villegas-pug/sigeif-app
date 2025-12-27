package microservice.cedif.domain.ports.in.integrantefamilia;

import java.util.List;
import microservice.cedif.domain.models.FamiliaIntegrante;

public interface IntegranteFamiliaServicePort {

   FamiliaIntegrante createIntegranteFamilia(FamiliaIntegrante integranteFamilia);

   FamiliaIntegrante updateIntegranteFamilia(FamiliaIntegrante integranteFamilia);

   <M> List<M> updateIntegrantesFamilia(List<FamiliaIntegrante> integrantesFamilia);

   FamiliaIntegrante findFamiliaIntegranteById(Long idIntegrante);

   <M> M deleteFamiliaIntegranteById(Long idIntegrante);

}
