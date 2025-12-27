package microservice.cedif.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.codigofamilia.CodigoFamiliaServicePort;
import microservice.cedif.domain.ports.out.CodigoFamiliaRepositoryPort;

@Service
@AllArgsConstructor
public class CodigoFamiliaService implements CodigoFamiliaServicePort {

   private final CodigoFamiliaRepositoryPort repository;

   @Override
   @Transactional
   public String generateCodFamilia(Long idFamilia) {
      return this.repository.generateCodFamilia(idFamilia);
   }

   @Override
   @Transactional
   public String generateCodIntegrante(Long idIntegrante) {
      return this.repository.generateCodIntegrante(idIntegrante);
   }

}
