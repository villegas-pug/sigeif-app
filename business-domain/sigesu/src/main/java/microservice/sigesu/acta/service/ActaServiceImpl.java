package microservice.sigesu.acta.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.sigesu.acta.model.Acta;
import microservice.sigesu.acta.repository.ActaRepository;

@Service
@AllArgsConstructor
public class ActaServiceImpl implements ActaService {

   private final ActaRepository repository;

   @Override
   @Transactional
   public void createActa(Acta acta) {
      this.repository.saveActa(acta);
   }

   @Override
   @Transactional
   public void updateActa(Acta acta) {
      this.repository.saveActa(acta);
   }

   @Override
   @Transactional(readOnly = true)
   public Acta findActaById(Long id) {
      return this.repository.findActaById(id).orElseThrow(NotFoundException::new);
   }

   @Override
   @Transactional
   public void deleteActaById(Long idActa) {
      this.repository.deleteActaById(idActa);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Acta> findActasByIdAliado(Long idAliado) {
      List<Acta> actas = this.repository.findActasByIdAliado(idAliado);
      if (actas.isEmpty()) {
         throw new NotFoundException();
      }

      return actas;
   }

}
