package microservice.punche.anexofase.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.anexofase.model.AnexoFase;
import microservice.punche.anexofase.repository.AnexoFaseRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class AnexoFaseServiceImpl implements AnexoFaseService {

   private final AnexoFaseRepository repository;

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
