package microservice.sigesu.aliado.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.shared_data.exceptions.NotFoundByIdException;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.sigesu.aliado.model.Aliado;
import microservice.sigesu.aliado.repository.AliadoRepository;

@Service
@AllArgsConstructor
public class AliadoServiceImpl implements AliadoService {

   private final AliadoRepository repository;

   @Override
   @Transactional
   public Aliado createAliado(Aliado aliado) {
      return this.repository.saveAliado(aliado);
   }

   @Override
   @Transactional
   public Aliado updateAliado(Aliado aliado) {
      return this.repository.saveAliado(aliado);
   }

   @Override
   @Transactional
   public void deleteAliadoById(Long idAliado) {
      this.repository.deleteAliadoById(idAliado);
   }

   @Override
   @Transactional(readOnly = true)
   public Aliado findAliadoById(Long idAliado) {
      return this.repository.findAliadoById(idAliado).orElseThrow(() -> new NotFoundByIdException(idAliado));
   }

   @Override
   @Transactional(readOnly = true)
   public List<Aliado> findAliadosByIdZona(Long idZona) {
      List<Aliado> aliados = this.repository.findAliadosByIdZona(idZona);
      if (aliados.isEmpty()) {
         throw new NotFoundException();
      }

      return aliados;
   }

}
