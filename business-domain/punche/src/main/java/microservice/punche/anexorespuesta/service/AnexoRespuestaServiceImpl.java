package microservice.punche.anexorespuesta.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.mappers.AnexoRespuestaCreateMapper;
import microservice.punche.anexorespuesta.mappers.AnexoRespuestaUpdateMapper;
import microservice.punche.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.anexorespuesta.repository.AnexoRespuestaRepository;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoRespuestaServiceImpl implements AnexoRespuestaService {

   private final AnexoRespuestaRepository repository;
   private final AnexoRespuestaUpdateMapper updateMapper;
   private final AnexoRespuestaCreateMapper createMapper;

   @Override
   @Transactional
   public AnexoRespuesta evaluarAnexoPreguntas(AnexoRespuesta respuesta) {
      AnexoRespuesta newAnexoRespuesta = this.repository.save(respuesta);
      return newAnexoRespuesta;
   }

   @Override
   @Transactional
   public <M> List<M> updateAnexosRespuestas(List<UpdateAnexoRespuestaRequest> anexosRespuestas) {
      List<AnexoRespuesta> updatedAnexosRespuestas = this.repository
            .saveAll(this.updateMapper.toModels(anexosRespuestas));

      if (anexosRespuestas.size() == 0) {
         throw new NotFoundException();
      }

      return (List<M>) updatedAnexosRespuestas;
   }

   @Override
   @Transactional
   public <M> List<M> createAnexosRespuestas(List<CreateAnexoRespuestaRequest> anexosRespuestas) {
      List<M> newAnexosRespuestas = this.repository.saveAll(this.createMapper.toModels(anexosRespuestas));

      if (newAnexosRespuestas.size() == 0) {// TODO: Crear un excepción para menejar el create ...
         throw new NotFoundException();
      }

      return (List<M>) newAnexosRespuestas;
   }

   @Override
   @Transactional(readOnly = true)
   public <M> List<M> findAnexosRespuestasByQuerys(GetAnexoRespuestaByParamsQuery query) {
      List<M> models = this.repository.findAnexosRespuestasByQuerys(query.getIdFamilia(), query.getAnexo(),
            query.getGrupo());
      if (models.size() == 0) {
         throw new NotFoundException();
      }

      return models;
   }

   @Override
   @Transactional(readOnly = true)
   public <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(GetIntegranteAnexoRespuestaByParamsQuery query) {
      List<M> models = this.repository.findIntegranteAnexosRespuestasByQuerys(query.getIdIntegrante(), query.getAnexo(),
            query.getGrupo());
      if (models.size() == 0) {
         throw new NotFoundException();
      }

      return models;
   }

   @Override
   @Transactional
   public void uploadAnexoRespuesta(AnexoRespuesta anexoRespuesta) {
      this.repository.save(anexoRespuesta);
   }

   @Override
   @Transactional(readOnly = true)
   public AnexoRespuesta findAnexoRespuestaById(Long idRespuesta) {
      return this.repository.findAnexoRespuestaById(idRespuesta)
            .orElseThrow(NotFoundException::new);
   }

   @Override
   @Transactional(readOnly = true)
   public List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante) {
      List<EstadoAnexoProjectionResponse> estadosAnexos = this.repository.findEstadosAnexosByParams(idFamilia,
            idIntegrante);
      if (estadosAnexos.size() == 0) {
         throw new NotFoundException();
      }
      return estadosAnexos;
   }

   @Override
   @Transactional(readOnly = true)
   public List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(Integer numAnexo,
         Long idFamilia, Long idIntegrante) {
      List<ReporteComparativoFasesFichaProjection> report = this.repository
            .generateComparativeReportForFichaFasesByFilters(numAnexo, idFamilia, idIntegrante);
      if (report.size() == 0) {
         throw new NotFoundException();
      }
      return report;
   }

   @Override
   @Transactional
   public void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante) {
      this.repository.deleteAnexoRespuestasByParams(numAnexo, fase, idFamilia, idIntegrante);
   }
}
