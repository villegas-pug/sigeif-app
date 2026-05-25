package microservice.sigesu.anexorespuesta.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.sigesu.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.exceptions.PersonalInvalidException;
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
import microservice.sigesu.persona.model.Persona;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.personal.service.PersonalService;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AnexoRespuestaServiceImpl implements AnexoRespuestaService {

   private static final String LDAP_VALIDATION_URL = "https://srvapp01.inabif.gob.pe:8443/sisserviciorest/rest/validacion/login/ldappost";

   private final AnexoRespuestaRepository repository;
   private final AnexoRespuestaUpdateMapper updateMapper;
   private final AnexoRespuestaCreateMapper createMapper;
   private final PersonalService personalService;

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
   public List<Map<String, Object>> listarResponsablesCentro(String nombreCentro, Long idUnidadOrganica) {
      return repository.listarResponsablesCentro(nombreCentro, idUnidadOrganica);
   }

   @Override
   @Transactional(readOnly = true)
   public boolean verifyPersonal(Integer idPersonal, String password) {

      // * Dep´s

      // * 1. Buscar personal
      Personal personal = this.personalService.findPersonalById(idPersonal);

      if (personal == null) {
         throw new NotFoundException();
      }

      String user = personal.getPersona().getUsuario().getLogin();

      // * 2. Verificar usuario
      try {
         URL url = new URL(LDAP_VALIDATION_URL);
         HttpURLConnection connection = (HttpURLConnection) url.openConnection();
         connection.setRequestMethod("POST");
         connection.setConnectTimeout(5000);
         connection.setReadTimeout(8000);
         connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
         connection.setDoOutput(true);

         String urlParameters = "user=" + URLEncoder.encode(user, StandardCharsets.UTF_8)
               + "&password=" + URLEncoder.encode(password, StandardCharsets.UTF_8);

         try (OutputStream os = connection.getOutputStream()) {
            byte[] input = urlParameters.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
         }

         int responseCode = connection.getResponseCode();

         InputStream is = (responseCode < HttpURLConnection.HTTP_BAD_REQUEST)
               ? connection.getInputStream()
               : connection.getErrorStream();

         try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
               response.append(responseLine.trim());
            }

            JsonNode responseJson = new ObjectMapper().readTree(response.toString());
            boolean estado = responseJson.path("estado").asBoolean(false);

            connection.disconnect();
            return responseCode == HttpURLConnection.HTTP_OK && estado;
         }

      } catch (Exception e) {
         return false;
      }

   }

   @Override
   @Transactional
   public void saveConformidadAnexoCabecera(Integer idCabecera, Integer estado) {
      this.repository.saveConformidadAnexoCabecera(idCabecera, estado);
   }

   @Override
   @Transactional
   public void validatePersonalAnexoCabecera(Integer idAnexoCabecera, String personal, String password) {

      Integer idPersonal = Arrays.stream(personal.split(","))
            .map(String::trim)
            .map(Integer::valueOf)
            .findFirst()
            .orElse(null);

      // * 1.
      boolean isValidPersonal = this.verifyPersonal(idPersonal, password);
      if (!isValidPersonal) {
         throw new PersonalInvalidException();
      }

      // * 2.
      this.repository.savePersonalValidaAnexoCabecera(idAnexoCabecera, personal);

   }

   @Override
   @Transactional
   public void resetValidacionAnexoCabecera(Integer idCabecera) {
      this.repository.savePersonalValidaAnexoCabecera(idCabecera, null);
   }

   @Override
   @Transactional
   public void insertarAnexoCabeceraAudio(Long idAnexoCabecera, byte[] audio, String nombreArchivo) {
      this.repository.insertarAnexoCabeceraAudio(idAnexoCabecera, audio, nombreArchivo);
   }

   @Override
   @Transactional
   public void actualizarAnexoCabeceraAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado) {
      this.repository.actualizarAnexoCabeceraAudio(idAudio, audio, nombreArchivo, estado);
   }

   @Override
   @Transactional
   public void eliminarAnexoCabeceraAudio(Long idAudio) {
      this.repository.eliminarAnexoCabeceraAudio(idAudio);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Map<String, Object>> consultarAnexoCabeceraAudio(Long idAnexoCabecera) {
      return this.repository.consultarAnexoCabeceraAudio(idAnexoCabecera);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Map<String, Object>> listarAnexoCabeceraAudio(Long idAnexoCabecera) {
      return this.repository.listarAnexoCabeceraAudio(idAnexoCabecera);
   }

}
