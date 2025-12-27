package microservice.cedif.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoFase;
import microservice.cedif.domain.ports.in.anexofase.AnexoFaseServicePort;
import microservice.cedif.domain.ports.out.AnexoFaseRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoFaseService implements AnexoFaseServicePort {

   private final AnexoFaseRepositoryPort repository;

   @Override
   @Transactional(readOnly = true)
   public List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo) {
      List<AnexoFase> anexoFases = this.repository.findAnexoFasesByNumAnexo(numAnexo);
      if (anexoFases.isEmpty()) {
         throw new NotFoundException();
      }

      return anexoFases;
   }

}
