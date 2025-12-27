package microservice.cedif.application.services.reporting;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.ports.in.anexorespuesta.AnexoRespuestaServicePort;
import microservice.cedif.domain.ports.in.anexorespuesta.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.cedif.domain.ports.in.integrantefamilia.IntegranteFamiliaServicePort;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaServicePort;
import microservice.cedif.domain.ports.in.reporting.AdmisionReportingServicePort;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.services.BaseReportingService;
import net.sf.jasperreports.engine.JRException;

@Service
@AllArgsConstructor
public class AdmisionJasperReportingService extends BaseReportingService implements AdmisionReportingServicePort {

      private final IntegranteFamiliaServicePort integranteFamiliaService;
      private final PotencialFamiliaServicePort potencialFamiliaService;
      private final AnexoRespuestaServicePort anexoRespuestaService;

      @Override
      @Transactional(readOnly = true)
      public byte[] generateFichaCompromisoResponsablePdf(Long idIntegrante) throws JRException {

            FamiliaIntegrante integranteFamilia = this.integranteFamiliaService.findFamiliaIntegranteById(idIntegrante);

            // * Parametros
            LocalDateTime fechaCompromiso = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            PotencialFamilia familia = this.potencialFamiliaService.findPotencialFamiliaById(
                        integranteFamilia.getFamilia().getIdFamilia());

            String nombresCuidador = familia.getIntegrantesFamilia()
                        .stream()
                        .filter(integrante -> integrante.getCuidador() == 1)
                        .findFirst()
                        .get()
                        .getNombresCompletos();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombresCuidador", nombresCuidador);
            parameters.put("nombresAcompañante", nombresCuidador);
            parameters.put("nombresNNA", integranteFamilia.getNombresCompletos());
            parameters.put("edadNNA", integranteFamilia.getEdad());
            parameters.put("domicilioNNA", integranteFamilia.getDireccion());
            parameters.put("documentoNNA", integranteFamilia.getNumeroDoc());
            parameters.put("fechaCompromiso", fechaCompromiso.format(formatter));

            // * Exportar a PDF
            return this.jasperReportingService.generatePdfReport("anexo_9_compromiso_responsable.jrxml", parameters);

      }

      @Override
      @Transactional(readOnly = true)
      public byte[] generateFichaAutorizacionIngresoNNAPdf(Long idIntegrante) throws JRException {

            // * Deps
            FamiliaIntegrante integrante = this.integranteFamiliaService.findFamiliaIntegranteById(idIntegrante);

            List<AnexoRespuestaQuery> anexoRespuestas = this.anexoRespuestaService
                        .findIntegranteAnexosRespuestasByQuerys(GetIntegranteAnexoRespuestaByParamsQuery
                                    .builder()
                                    .idIntegrante(idIntegrante.intValue())
                                    .anexo(11) // * Ficha de Autorizacion de Ingreso NNA
                                    .build());

            List<EstadoAnexoProjectionResponse> estadosAnexo = this.anexoRespuestaService.findEstadosAnexosByParams(
                        null,
                        idIntegrante);

            // * Param's
            LocalDateTime fechaHoraActa = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            String fechaActa = fechaHoraActa.format(formatter).split(" ")[0];
            String horaActa = fechaHoraActa.format(formatter).split(" ")[1];
            String nombresIntegrante = integrante.getNombresCompletos();

            StringBuilder requisitos = new StringBuilder();
            estadosAnexo
                        .stream()
                        .filter(anexoRespuesta -> anexoRespuesta.getNumGrupo() == -1
                                    || anexoRespuesta.getNumGrupo() == -3)
                        .filter(anexoRespuesta -> anexoRespuesta.getEstado() == 1)
                        .sorted(Comparator.comparing(EstadoAnexoProjectionResponse::getIdPregunta))
                        .map(anexoRespuesta -> anexoRespuesta.getPregunta())
                        .forEach(pregunta -> requisitos.append(". ").append(pregunta).append(".").append("\n"));

            String observaciones = anexoRespuestas
                        .stream()
                        .filter(anexoRespuesta -> anexoRespuesta.getIdPregunta() == 306)
                        .map(AnexoRespuestaQuery::getRespuesta)
                        .filter(Objects::nonNull)
                        .findFirst()
                        .orElse("");

            Map<String, Object> parametros = new HashMap<>();
            parametros.put("fechaActa", fechaActa);
            parametros.put("horaActa", horaActa);
            parametros.put("nombresIntegrante", nombresIntegrante);
            parametros.put("requisitos", requisitos.toString());
            parametros.put("observaciones", observaciones);

            return super.jasperReportingService.generatePdfReport(
                        "anexo_11_autorizacion_ingreso_nna_y_adolecente.jrxml",
                        parametros);

      }

      @Override
      protected String getBaseTemplatePath() {
            return "/templates/2.admision/";
      }

}
