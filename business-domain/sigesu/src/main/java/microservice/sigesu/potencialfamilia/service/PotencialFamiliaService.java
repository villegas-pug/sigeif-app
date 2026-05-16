package microservice.sigesu.potencialfamilia.service;

import java.util.Map;
import java.util.Set;

import microservice.sigesu.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.sigesu.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.sigesu.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.dtos.responses.PotencialFamiliaWithEstadoAnexosResponse;

public interface PotencialFamiliaService {

      void createPotecialFamilia(CreatePotencialFamiliaRequest potencialFamilia);

      void updatePotencialFamilia(UpdatePotencialFamiliaRequest potencialFamilia);

      PotencialFamilia partialUpdatePotecialFamilia(UpdatePartialPotecialFamiliaRequest potencialFamilia);

      PotencialFamiliaResponse findPotencialFamiliaById(Long idFamilia);

      void deletePotencialFamiliaById(Long idFamilia);

      PotencialFamiliaWithEstadoAnexosResponse findPotencialFamiliaWithEstadoAnexosResponseByIdFamilia(Long idFamilia);

      Map<Long, PotencialFamiliaWithEstadoAnexosResponse> findPotencialesFamiliasWithEstadoAnexosResponseByIdsFamilia(
                  Set<Long> idsFamilias);

      PotencialFamiliaWithEstadoAnexosResponse extractPotencialFamiliaWithEstadoAnexoOfPotencialFamilia(
                  PotencialFamiliaResponse potencialFamilia);

}