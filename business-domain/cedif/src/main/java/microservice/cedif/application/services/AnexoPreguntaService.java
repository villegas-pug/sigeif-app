package microservice.cedif.application.services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoPregunta;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.ports.in.anexopregunta.AnexoPreguntaServicePort;
import microservice.cedif.domain.ports.in.integrantefamilia.IntegranteFamiliaServicePort;
import microservice.cedif.domain.ports.out.AnexoPregutaRepositoryPort;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoPreguntaService implements AnexoPreguntaServicePort {

   private final AnexoPregutaRepositoryPort repository;
   private final IntegranteFamiliaServicePort integranteFamiliaService;

   @Override
   @Transactional(readOnly = true)
   public List<AnexoPregunta> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo) {

      List<AnexoPregunta> preguntas = this.repository.findAllAnexoPregustasByParams(idServicio, anexo, grupo);
      if (preguntas.size() == 0) {
         throw new NotFoundException();
      }

      return preguntas;
   }

   @Override
   @Transactional(readOnly = true)
   public List<AnexoPregunta> findAllAnexoPregustasOfIntegranteByParams(Integer anexo,
         Long idIntegrante) {

      // * 1 Calcula total meses desde la fecha de nacimiento a hoy:
      FamiliaIntegrante integrante = this.integranteFamiliaService.findFamiliaIntegranteById(idIntegrante);

      LocalDate fecNacIntegrante = integrante.getFecNac();
      Long integranteTotalMonthsOfAge = Period.between(fecNacIntegrante, LocalDate.now()).toTotalMonths();

      // * 2. Extrae preguntas que representan `flags` de la Ficha.
      Integer idServicio = InabifServices.CEDIF.getId().intValue();
      List<AnexoPregunta> anexoPreguntasFlags = this.findAllAnexoPregustasByParams(idServicio, anexo, null)
            .stream()
            .filter(anexoPregunta -> anexoPregunta.getNumGrupo() <= 0)
            .toList();

      // * 3. ...
      List<AnexoPregunta> anexoPreguntas = switch (integranteTotalMonthsOfAge) {
         // * FICHA 13:
         // * 1 año a 1 año 6 meses
         case Long meses when meses >= 12 && meses <= 18 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 1);
         // * 1 año 6 meses a 2 años
         case Long meses when meses > 18 && meses <= 24 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 2);
         // * 2 años a 2 años 11 meses
         case Long meses when meses > 24 && meses < 36 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 3);
         // * 3 años a 3 años 11 meses
         case Long meses when meses >= 36 && meses < 48 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 4);
         // * 4 años a 4 años 11 meses
         case Long meses when meses >= 48 && meses < 60 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 5);
         // * 5 años a 5 años 11 meses
         case Long meses when meses >= 60 && meses < 72 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 6);
         // * 6 años a 17 años 11 meses
         case Long meses when meses >= 72 && meses <= 215 -> this.findAllAnexoPregustasByParams(idServicio, anexo, 7);
         default -> List.of();
      };

      anexoPreguntasFlags.forEach(anexoPreguntas::add);
      anexoPreguntas.sort(
            Comparator
                  .comparing(AnexoPregunta::getNumGrupo)
                  .thenComparing(AnexoPregunta::getNumPregunta));
      return anexoPreguntas;
   }

}