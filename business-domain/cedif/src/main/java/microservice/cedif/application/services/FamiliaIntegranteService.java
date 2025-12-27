package microservice.cedif.application.services;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.ports.in.codigofamilia.CodigoFamiliaServicePort;
import microservice.cedif.domain.ports.in.integrantefamilia.IntegranteFamiliaServicePort;
import microservice.cedif.domain.ports.out.IntegranteFamiliaRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class FamiliaIntegranteService implements IntegranteFamiliaServicePort {

   private final IntegranteFamiliaRepositoryPort repository;
   private final CodigoFamiliaServicePort codigoFamiliaService;

   @Override
   @Transactional
   public <M> List<M> updateIntegrantesFamilia(List<FamiliaIntegrante> integrantesFamilia) {

      // * 1. Extrae IDs integrantes existentes
      Set<Long> idsIntegrantesOld = integrantesFamilia
            .stream()
            .map(FamiliaIntegrante::getIdIntegrante)
            .filter(Objects::nonNull)
            .collect(Collectors.toSet());

      // * 2. Guardar integrantes
      List<FamiliaIntegrante> integrantesUpdated = this.repository.saveAll(integrantesFamilia);

      // * 3. Generar códigos temporal solo para nuevos integrantes
      integrantesUpdated
            .stream()
            .map(FamiliaIntegrante::getIdIntegrante)
            .filter(idIntegrante -> !idsIntegrantesOld.contains(idIntegrante))
            .forEach(this.codigoFamiliaService::generateCodIntegrante);

      return (List<M>) integrantesUpdated;
   }

   @Override
   @Transactional
   public <M> M deleteFamiliaIntegranteById(Long idIntegrante) {
      M model = this.repository.deleteFamiliaIntegranteById(idIntegrante);
      return (M) model;
   }

   @Override
   @Transactional
   public FamiliaIntegrante createIntegranteFamilia(FamiliaIntegrante integranteFamilia) {
      FamiliaIntegrante newIntegrante = this.repository.save(integranteFamilia);
      return newIntegrante;
   }

   @Override
   @Transactional
   public FamiliaIntegrante updateIntegranteFamilia(FamiliaIntegrante integranteFamilia) {
      return this.repository.save(integranteFamilia);
   }

   @Override
   @Transactional(readOnly = true)
   public FamiliaIntegrante findFamiliaIntegranteById(Long idIntegrante) {
      return this.repository.findFamiliaIntegranteById(idIntegrante).orElseThrow(NotFoundException::new);
   }
}
