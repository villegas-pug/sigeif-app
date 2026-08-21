package microservice.punche.reporting.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.service.AnexoRespuestaService;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.persona.model.Persona;
import microservice.punche.personal.model.Personal;
import microservice.punche.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.punche.potencialfamilia.service.PotencialFamiliaService;
import microservice.punche.reporting.dtos.CompromisoFamiliarPdfData;
import microservice.punche.zona.dtos.ZonaIntervencionParamsDto;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.punche.zona.service.ZonaIntervencionService;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.helpers.DateHelper;
import microservice.shared_data.services.BaseReportingService;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class PlanificacionReportingServiceImpl extends BaseReportingService
            implements PlanificacionReportingService {

      private final PotencialFamiliaService potencialFamiliaService;
      private final ZonaIntervencionService zonaIntervencionService;
      private final AnexoRespuestaService anexoRespuestaService;
      private final DateHelper dateHelper;
      private final CompromisoFamiliarOpenPdfService compromisoFamiliarOpenPdfService;

      @Override
      @Transactional(readOnly = true)
      public byte[] generateCompromisoFamiliarAsPdf(Long idFamilia) {

            // * 1. Deps
            PotencialFamiliaResponse potencialFamilia = this.potencialFamiliaService
                        .findPotencialFamiliaById(idFamilia);

            Map<Integer, Object> mapRespuestasCompromisoFamiliar = this.anexoRespuestaService
                        .findAnexosRespuestasByQuerys(
                                    GetAnexoRespuestaByParamsQuery.builder()
                                                .idFamilia(idFamilia.intValue())
                                                .anexo(14) // * Compromiso Familiar
                                                .grupo(1)
                                                .build())
                        .stream()
                        .map(anexoRespuesta -> (AnexoRespuestaQuery) anexoRespuesta)
                        .collect(Collectors.toMap(AnexoRespuestaQuery::getIdPregunta,
                                    AnexoRespuestaQuery::getRespuesta));

            LocalDate fechaCompromiso = this.dateHelper
                        .parseToLocalDate(mapRespuestasCompromisoFamiliar.get(1719), LocalDate.now());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // * 2. Acompañante
            Persona acompañante = Optional.ofNullable(potencialFamilia.getAcompañante())
                        .map(Personal::getPersona)
                        .map(persona -> {
                              String nombres = persona.getNombres()
                                          .concat(" ")
                                          .concat(persona.getApePaterno())
                                          .concat(" ")
                                          .concat(persona.getApeMaterno())
                                          .trim();
                              persona.setNombres(nombres.toUpperCase());
                              return persona;
                        })
                        .orElse(Persona.builder().nombres("-").numeroDoc("-").build());

            // * 3. Cuidador
            FamiliaIntegrante cuidador = potencialFamilia.getIntegrantesFamilia().stream()
                        .filter(integrante -> integrante.getCuidador().equals(1))
                        .findFirst()
                        .map(integrante -> {
                              String nombres = integrante.getNombres()
                                          .concat(" ")
                                          .concat(integrante.getPrimerApe())
                                          .concat(" ")
                                          .concat(integrante.getSegundoApe())
                                          .trim();
                              integrante.setNombres(nombres.toUpperCase());
                              return integrante;
                        })
                        .orElse(FamiliaIntegrante.builder().nombres("-").numeroDoc("-").build());

            CompromisoFamiliarPdfData data = new CompromisoFamiliarPdfData(
                        cuidador.getNombres(),
                        cuidador.getNumeroDoc(),
                        acompañante.getNombres(),
                        acompañante.getNumeroDoc(),
                        fechaCompromiso.format(formatter));

            // * Exportar a PDF
            return this.compromisoFamiliarOpenPdfService.generate(data);

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
