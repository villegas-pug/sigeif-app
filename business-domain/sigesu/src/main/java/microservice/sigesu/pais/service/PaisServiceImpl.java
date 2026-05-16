package microservice.sigesu.pais.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.sigesu.pais.repository.PaisRepository;
import microservice.shared_data.entities.PaisEntity;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class PaisServiceImpl implements PaisService {

   private final PaisRepository repository;

   @Transactional(readOnly = true)
   public List<PaisEntity> findAllPais() {
      List<PaisEntity> paises = repository.findByNacionalidadIsNotNull();
      if (paises.size() == 0) {
         throw new NotFoundException();
      }
      return paises;
   }

}
