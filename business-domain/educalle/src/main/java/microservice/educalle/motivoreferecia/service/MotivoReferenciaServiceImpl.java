package microservice.educalle.motivoreferecia.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.educalle.motivoreferecia.model.MotivoReferecia;
import microservice.educalle.motivoreferecia.repository.MotivoReferenciaRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class MotivoReferenciaServiceImpl implements MotivoReferenciaService {

   private MotivoReferenciaRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<MotivoReferecia> findAllMotivosReferencia() {
      List<MotivoReferecia> models = this.repository.findAllMotivosReferencia();

      if (models.size() == 0) {
         throw new NotFoundException();
      }

      return models;
   }

}
