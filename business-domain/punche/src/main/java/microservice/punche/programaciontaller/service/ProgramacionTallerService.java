package microservice.punche.programaciontaller.service;

import java.util.List;

import microservice.punche.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.punche.programaciontaller.model.ProgramacionTaller;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;

public interface ProgramacionTallerService {

      ProgramacionTaller createProgramacionTaller(ProgramacionTaller programacionTaller);

      ProgramacionTaller updateProgramacionTaller(ProgramacionTaller programacionTaller);

      void deleteProgramacionTallerById(Long idProgTaller);

      ProgramacionTallerResponse findProgramacionTallerById(Long idProgTaller);

      ProgramacionTaller findProgramacionTallerById(Integer idProgTaller);

      public List<ProgramacionTallerProjectionResponse> findProgramacionTalleresByParams(Integer idServicio,
                  Integer anio,
                  Integer mes,
                  Integer idZona);

      void uploadAnexoProgramacionTaller(Long idProgTaller, String anexoName, byte[] anexo);

}
