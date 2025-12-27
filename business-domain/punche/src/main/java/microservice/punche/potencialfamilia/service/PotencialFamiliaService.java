package microservice.punche.potencialfamilia.service;

import java.util.Map;
import java.util.Set;

import microservice.punche.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.punche.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.punche.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
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