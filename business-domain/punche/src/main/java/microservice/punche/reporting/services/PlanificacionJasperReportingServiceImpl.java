package microservice.punche.reporting.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.persona.model.Persona;
import microservice.punche.personal.model.Personal;
import microservice.punche.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.punche.potencialfamilia.service.PotencialFamiliaService;
import microservice.punche.zona.dtos.ZonaIntervencionParamsDto;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.punche.zona.service.ZonaIntervencionService;
import microservice.shared_data.services.BaseReportingService;
import net.sf.jasperreports.engine.JRException;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PlanificacionJasperReportingServiceImpl extends BaseReportingService
            implements PlanificacionReportingService {

      private final PotencialFamiliaService potencialFamiliaService;
      private final ZonaIntervencionService zonaIntervencionService;

      @Override
      @Transactional(readOnly = true)
      public byte[] generateCompromisoFamiliarAsPdf(Long idFamilia) throws JRException {

            // * 1. Deps
            PotencialFamiliaResponse potencialFamilia = this.potencialFamiliaService
                        .findPotencialFamiliaById(idFamilia);

            // CodigoFamilia codigoFamilia = potencialFamilia.getCodigoFamilia();

            LocalDateTime fechaCompromiso = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            // * 2. Acompañante
            Persona acompañante = Optional.ofNullable(potencialFamilia.getAcompañante())
                        .map(Personal::getPersona)
                        .orElse(null);

            String nombresCompletosAcompañante = Optional.ofNullable(acompañante)
                        .map(personal -> personal.getNombres()
                                    .concat(" ")
                                    .concat(personal.getApePaterno())
                                    .concat(" ")
                                    .concat(personal.getApeMaterno())
                                    .trim())
                        .orElse("");

            // * 3. Cuidador
            FamiliaIntegrante cuidador = potencialFamilia.getIntegrantesFamilia().stream()
                        .filter(integrante -> integrante.getCuidador().equals(1))
                        .findFirst()
                        .orElse(FamiliaIntegrante.builder().build());

            String nombresCompletosCuidador = Optional.ofNullable(cuidador)
                        .map(integrante -> integrante.getNombres()
                                    .concat(" ")
                                    .concat(integrante.getPrimerApe())
                                    .concat(" ")
                                    .concat(integrante.getSegundoApe())
                                    .trim())
                        .orElse("");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombresCuidador", nombresCompletosCuidador);
            parameters.put("numDocCuidador", cuidador.getNumeroDoc());
            parameters.put("nombresAcompañante", nombresCompletosAcompañante);
            parameters.put("numDocAcompañante", acompañante.getNumeroDoc());
            parameters.put("fechaCompromiso", fechaCompromiso.format(formatter));

            // * Exportar a PDF
            return this.jasperReportingService.generatePdfReport("compromiso_familiar.jrxml", parameters);

      }

      @Override
      @Transactional(readOnly = true)
      public byte[] generateZonaIntervencionExcelReportByParams(ZonaIntervencionParamsDto params) {

            List<ZonaIntervencion> zonaIntervencion = this.zonaIntervencionService
                        .findZonasIntervencionByParams(params.getDescripcionZona(), params.getAnioRegistroZona(),
                                    params.getMesRegistroZona());

            // * 1. ...
            List<Map<String, Object>> zonaIntervencionAdapter = zonaIntervencion
                        .stream()
                        .map(zona -> {

                              Map<String, Object> record = new HashMap<>();

                              record.put("CÓDIGO", zona.getIdUbigeo());
                              record.put("AÑO", zona.getFecRegistra().getYear());
                              record.put("ZONA INTERVENCION", zona.getDescripcion());
                              record.put("CUIDADOR PRINCIPAL", zona.getIdUbigeo());
                              record.put("FECHA REGISTRO", zona.getFecRegistra());

                              return record;

                        }).toList();

            // * 2 ...
            return super.apachePOIReportingService.generateDynamicExcelFile("░", zonaIntervencionAdapter);

      }

      @Override
      protected String getBaseTemplatePath() {
            return "/templates/1.planificacion/";
      }

}
