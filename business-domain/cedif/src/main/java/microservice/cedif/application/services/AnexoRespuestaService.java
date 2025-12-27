package microservice.cedif.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.cedif.domain.ports.in.anexorespuesta.AnexoRespuestaServicePort;
import microservice.cedif.domain.ports.in.anexorespuesta.GetAnexoRespuestaByParamsQuery;
import microservice.cedif.domain.ports.in.anexorespuesta.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.cedif.domain.ports.out.AnexoRespuestaRepositoryPort;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoRespuestaService implements AnexoRespuestaServicePort {

   private final AnexoRespuestaRepositoryPort repository;

   @Override
   @Transactional
   public AnexoRespuesta evaluarAnexoPreguntas(AnexoRespuesta respuesta) {
      AnexoRespuesta newAnexoRespuesta = this.repository.save(respuesta);
      return newAnexoRespuesta;
   }

   @Override
   @Transactional
   public <M> List<M> updateAnexosRespuestas(List<AnexoRespuesta> anexosRespuestas) {
      List<AnexoRespuesta> anexos = this.repository.saveAll(anexosRespuestas);

      if (anexos.size() == 0) {// TODO: Crear un excepción para menejar el update ...
         throw new NotFoundException();
      }

      return (List<M>) anexos;
   }

   @Override
   @Transactional
   public <M> List<M> createAnexosRespuestas(List<AnexoRespuesta> anexosRespuestas) {
      List<M> models = this.repository.saveAll(anexosRespuestas);

      if (models.size() == 0) {// TODO: Crear un excepción para menejar el create ...
         throw new NotFoundException();
      }

      return (List<M>) models;
   }

   @Override
   @Transactional(readOnly = true)
   public List<AnexoRespuestaQuery> findAnexosRespuestasByQuerys(GetAnexoRespuestaByParamsQuery query) {
      List<AnexoRespuestaQuery> models = this.repository.findAnexosRespuestasByQuerys(query.getIdFamilia(),
            query.getAnexo(),
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
