package microservice.cedif.application.services;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.Catalogo;
import microservice.cedif.domain.ports.in.catalogo.CatalogoServicePort;
import microservice.cedif.domain.ports.out.CatalogoRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class CatalogoService implements CatalogoServicePort {

   private final CatalogoRepositoryPort repository;

   @Override
   @Transactional(readOnly = true)
   public List<Catalogo> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo) {
      List<Catalogo> catalogos = this.repository.findAllCatalogosByGrupos(grupo, subgrupo);
      if (catalogos.size() == 0) {
         throw new NotFoundException();
      }
      return catalogos;
   }

}
