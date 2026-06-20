package microservice.punche.programaciontaller.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.detpatfam.repository.DetPatfamRepository;
import microservice.punche.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.punche.programaciontaller.mappers.ProgramacionTallerResponseMapper;
import microservice.punche.programaciontaller.model.ProgramacionTaller;
import microservice.punche.programaciontaller.repository.ProgramacionTallerRepository;
import microservice.shared_data.dtos.responses.DetPatfamProjectionResponse;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class ProgramacionTallerServiceImpl implements ProgramacionTallerService {

   private final ProgramacionTallerRepository repository;
   private final ProgramacionTallerResponseMapper responseMapper;
   private final DetPatfamRepository detPatfamRepository;

   @Override
   @Transactional
   public ProgramacionTaller createProgramacionTaller(ProgramacionTaller programacionTaller) {
      return this.repository.createProgramacionTaller(programacionTaller);
   }

   @Override
   @Transactional
   public ProgramacionTaller updateProgramacionTaller(ProgramacionTaller programacionTaller) {
      return this.repository.updateProgramacionTaller(programacionTaller);
   }

   @Override
   @Transactional(readOnly = true)
   public ProgramacionTallerResponse findProgramacionTallerById(Long idProgTaller) {
      ProgramacionTallerResponse programacionTallerResponse = this.repository.findProgramacionTallerById(idProgTaller)
            .map(this.responseMapper::toResponse)
            .orElseThrow(NotFoundException::new);

      Integer idTaller = programacionTallerResponse.getTaller().getIdTaller();
      DetPatfamProjectionResponse detPatfam = this.detPatfamRepository.findDetPatfamByIdTaller(idTaller).getFirst();
      programacionTallerResponse.setDetPatfam(detPatfam);

      return programacionTallerResponse;

   }

   @Override
   @Transactional(readOnly = true)
   public ProgramacionTaller findProgramacionTallerById(Integer idProgTaller) {
      return this.repository.findProgramacionTallerById(idProgTaller.longValue())
            .orElseThrow(NotFoundException::new);

   }

   @Override
   @Transactional(readOnly = true)
   public List<ProgramacionTallerProjectionResponse> findProgramacionTalleresByParams(Integer idServicio, Integer anio,
         Integer mes, Integer idZona) {
      List<ProgramacionTallerProjectionResponse> programacionTalleres = this.repository
            .findProgramacionTalleresByParams(idServicio, anio, mes, idZona);

      if (programacionTalleres.isEmpty()) {
         throw new NotFoundException();
      }
      return programacionTalleres;

   }

   @Override
   @Transactional
   public void deleteProgramacionTallerById(Long idProgTaller) {
      this.repository.deleteProgramacionTallerById(idProgTaller);
   }

   @Override
   @Transactional
   public void uploadAnexoProgramacionTaller(Long idProgTaller, String anexoName, byte[] anexo) {
      this.repository.uploadAnexoProgramacionTaller(idProgTaller, anexoName, anexo);
   }

}
