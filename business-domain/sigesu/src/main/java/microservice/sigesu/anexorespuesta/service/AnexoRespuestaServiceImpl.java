package microservice.sigesu.anexorespuesta.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.sigesu.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.mappers.AnexoRespuestaCreateMapper;
import microservice.sigesu.anexorespuesta.mappers.AnexoRespuestaUpdateMapper;
import microservice.sigesu.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.sigesu.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoCompletoRequest;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.sigesu.anexorespuesta.repository.AnexoRespuestaRepository;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.sigesu.anexorespuesta.util.PdfGenerator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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

   @Override
   @Transactional
   public AnexoEvaluacionResponse crearAnexoCompleto(
         CreateAnexoEvaluacionRequest request) {

      return repository.crearAnexoCompleto(request);
   }

   @Override
   public List<AnexoCabeceraResponse> listarAnexosCabecera() {
      return repository.listarAnexosCabecera();
   }

   @Override
   public Map<String, Object> obtenerRespuestas(Long idAnexoCabecera, Integer correlativo) {
      return repository.obtenerRespuestasPorAnexo(idAnexoCabecera, correlativo);
   }

   @Override
   @Transactional
   public AnexoEvaluacionResponse updateAnexoCompleto(UpdateAnexoCompletoRequest request) {
      return repository.updateAnexoCompleto(request);
   }

   @Override
   public byte[] generarPdf(Long idAnexoCabecera, Integer correlativo) {

      Map<String, Object> data = repository.obtenerRespuestasPorAnexo(idAnexoCabecera, correlativo);

      // 🔥 obtener lista de respuestas
      List<Map<String, Object>> respuestas = (List<Map<String, Object>>) data.get("respuestas");

      int conforme = 0;
      int noConforme = 0;
      int observacion = 0;
      int noAplica = 0;

      if (respuestas != null) {

         for (Map<String, Object> r : respuestas) {

            Object respObj = r.get("respuesta");

            if (respObj == null)
               continue;

            String valor = respObj.toString()
                  .toUpperCase()
                  .replace("Á", "A")
                  .replace("É", "E")
                  .replace("Í", "I")
                  .replace("Ó", "O")
                  .replace("Ú", "U")
                  .trim();

            if (valor.equals("CONFORME"))
               conforme++;
            else if (valor.equals("NO CONFORME"))
               noConforme++;
            else if (valor.equals("OBSERVACION"))
               observacion++;
            else if (valor.equals("NO APLICA"))
               noAplica++;
         }
      }

      // 🔥 agregar totales al map
      data.put("TOTAL_CONFORME", conforme);
      data.put("TOTAL_NO_CONFORME", noConforme);
      data.put("TOTAL_OBSERVACION", observacion);
      data.put("TOTAL_NO_APLICA", noAplica);
      data.get("tipoCentro");
      data.get("responsableSupervision");
      data.get("respDirector");
      data.get("supervisado");

      return PdfGenerator.generar(data);
   }

   /*
    * @Override
    * public byte[] generarPdf(Long idAnexoCabecera, Integer correlativo) {
    * 
    * Map<String, Object> data =
    * repository.obtenerRespuestasPorAnexo(idAnexoCabecera, correlativo);
    * 
    * return PdfGenerator.generar(data);
    * }
    */
   @Override
   @Transactional
   public void guardarAudio(MultipartFile file, Long idAnexoCabecera) {

      try {

         String carpeta = "C:/data/sigesu/uploads/audios/";
         Files.createDirectories(Paths.get(carpeta));

         String nombreArchivo = idAnexoCabecera + "_" + UUID.randomUUID() + ".mp3";

         Path ruta = Paths.get(carpeta + nombreArchivo);

         Files.write(ruta, file.getBytes());

         repository.actualizarAudio(idAnexoCabecera, nombreArchivo);
         System.out.println("Ruta absoluta: " + Paths.get(carpeta).toAbsolutePath());

      } catch (Exception e) {
         throw new RuntimeException("Error guardando audio", e);
      }
   }

   @Override
   public List<Map<String, Object>> listarResponsablesSupervision(String abreviatura) {
      return repository.listarResponsablesSupervision(abreviatura);
   }

   @Override
   public List<Map<String, Object>> listarResponsablesCentro(String nombreCentro, String nombrePersona) {
      return repository.listarResponsablesCentro(nombreCentro, nombrePersona);
   }
}
