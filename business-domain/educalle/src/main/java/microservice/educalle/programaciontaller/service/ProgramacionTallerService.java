package microservice.educalle.programaciontaller.service;

import java.util.List;

import microservice.educalle.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.educalle.programaciontaller.model.ProgramacionTaller;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;

public interface ProgramacionTallerService {

      ProgramacionTaller createProgramacionTaller(ProgramacionTaller programacionTaller);

      ProgramacionTaller updateProgramacionTaller(ProgramacionTaller programacionTaller);

      void deleteProgramacionTallerById(Long idProgTaller);

      ProgramacionTallerResponse findProgramacionTallerById(Long idProgTaller);

      ProgramacionTaller findProgramacionTallerById(Integer idProgTaller);

      public List<ProgramacionTallerProjectionResponse> findProgramacionTalleresByParams(Integer idServicio,
                  Integer anio,
                  Integer mes);

      void uploadAnexoProgramacionTaller(Long idProgTaller, String anexoName, byte[] anexo);

}
