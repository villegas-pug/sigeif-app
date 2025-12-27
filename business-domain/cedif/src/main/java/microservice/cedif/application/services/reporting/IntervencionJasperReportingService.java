package microservice.cedif.application.services.reporting;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.ports.in.anexorespuesta.AnexoRespuestaServicePort;
import microservice.cedif.domain.ports.in.anexorespuesta.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.cedif.domain.ports.in.integrantefamilia.IntegranteFamiliaServicePort;
import microservice.cedif.domain.ports.in.reporting.IntervencionReportingServicePort;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.services.BaseReportingService;
import net.sf.jasperreports.engine.JRException;

@Service
@AllArgsConstructor
public class IntervencionJasperReportingService extends BaseReportingService
            implements IntervencionReportingServicePort {

      private final IntegranteFamiliaServicePort integranteFamiliaService;
      private final AnexoRespuestaServicePort anexoRespuestaService;

      @Override
      public byte[] generateFichaEgresoCedifPdf(Long idIntegrante) throws JRException {

            // * Deps
            FamiliaIntegrante integrante = this.integranteFamiliaService.findFamiliaIntegranteById(idIntegrante);

            Map<Integer, Object> anexoRespuestasMap = this.anexoRespuestaService
                        .findIntegranteAnexosRespuestasByQuerys(
                                    GetIntegranteAnexoRespuestaByParamsQuery
                                                .builder()
                                                .idIntegrante(idIntegrante.intValue())
                                                .anexo(17) // * Ficha de egreso del CEDIF
                                                .build())
                        .stream()
                        .map(anexoRespuesta -> (AnexoRespuestaQuery) anexoRespuesta)
                        .collect(Collectors.toMap(AnexoRespuestaQuery::getIdPregunta,
                                    AnexoRespuestaQuery::getRespuesta));

            // * Param's
            String nombresIntegrante = integrante.getNombresCompletos().replace("-", "");
            int edad = integrante.getEdad();
            String sexo = integrante.getSexo().getCatDescripcion();
            String numeroDocumento = integrante.getNumeroDoc();
            String domicilio = Optional.ofNullable(integrante.getDireccion()).orElse("-");

            /*
             * fechaIngreso | 1445
             * tiempoPermanencia | 1448
             * motivoIngreso | 1450
             * principalesLogros | 1452
             * analisisIntegrado | 1454
             * nombresConQuienEgresaNNA | 1456
             * recomendaciones | 1457
             */

            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("nombresIntegrante", nombresIntegrante);
            parametros.put("edad", edad);
            parametros.put("sexo", sexo);
            parametros.put("numeroDocumento", numeroDocumento);
            parametros.put("domicilio", domicilio);
            parametros.put("fechaIngreso", Optional.ofNullable(anexoRespuestasMap.get(1445)).orElse("-"));
            parametros.put("tiempoPermanencia", Optional.ofNullable(anexoRespuestasMap.get(1448)).orElse("-"));
            parametros.put("motivoIngreso", anexoRespuestasMap.get(1450));
            parametros.put("principalesLogros", anexoRespuestasMap.get(1452));
            parametros.put("analisisIntegrado", anexoRespuestasMap.get(1454));
            parametros.put("nombresConQuienEgresaNNA", anexoRespuestasMap.get(1456));
            parametros.put("recomendaciones", anexoRespuestasMap.get(1457));

            return super.jasperReportingService.generatePdfReport("anexo_17_egreso_del_cedif.jrxml", parametros);
      }

      @Override
      protected String getBaseTemplatePath() {
            return "/templates/3.intervencion/";
      }

}
