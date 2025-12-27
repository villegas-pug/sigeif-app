package microservice.cedif.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Pais;
import microservice.cedif.domain.ports.in.pais.PaisServicePort;
import microservice.cedif.domain.ports.out.PaisRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class PaisService implements PaisServicePort {

   private final PaisRepositoryPort repository;

   @Transactional(readOnly = true)
   public List<Pais> findAllPais() {
      List<Pais> paises = this.repository.findAllByNacionalidadIsNotNull();
      if (paises.size() == 0) {
         throw new NotFoundException();
      }
      return paises;
   }

}
