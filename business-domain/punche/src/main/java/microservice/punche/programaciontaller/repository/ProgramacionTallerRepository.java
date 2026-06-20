package microservice.punche.programaciontaller.repository;

import java.util.List;
import java.util.Optional;

import microservice.punche.programaciontaller.model.ProgramacionTaller;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;

public interface ProgramacionTallerRepository {

      ProgramacionTaller createProgramacionTaller(ProgramacionTaller programacionTaller);

      ProgramacionTaller updateProgramacionTaller(ProgramacionTaller programacionTaller);

      void deleteProgramacionTallerById(Long idProgTaller);

      Optional<ProgramacionTaller> findProgramacionTallerById(Long idProgTaller);

      List<ProgramacionTallerProjectionResponse> findProgramacionTalleresByParams(Integer idServicio, Integer anio,
                  Integer mes, Integer idZona);

      void uploadAnexoProgramacionTaller(Long idProgTaller, String anexoName, byte[] anexo);

}
