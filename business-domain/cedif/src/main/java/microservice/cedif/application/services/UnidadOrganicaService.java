package microservice.cedif.application.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.UnidadOrganica;
import microservice.cedif.domain.ports.in.unidadorganica.UnidadOrganicaServicePort;
import microservice.cedif.domain.ports.out.UnidadOrganicaRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class UnidadOrganicaService implements UnidadOrganicaServicePort {

   private final UnidadOrganicaRepositoryPort repository;

   @Override
   @Transactional(readOnly = true)
   public List<UnidadOrganica> findUnidadesOrganicasByNombreReferencia(String ref) {
      List<UnidadOrganica> unidadesOrganicas = this.repository.findUnidadesOrganicasByNombreReferencia(ref);
      if (unidadesOrganicas.isEmpty()) {
         throw new NotFoundException();
      }

      return unidadesOrganicas;

   }

}
