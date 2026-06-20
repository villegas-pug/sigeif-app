package microservice.educalle.catalogo.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.catalogo.dtos.CatalogoDto;
import microservice.educalle.catalogo.repository.CatalogoRepositoryImpl;
import microservice.educalle.pais.repository.PaisRepository;
import microservice.shared_data.entities.PaisEntity;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class CatalogoServiceImpl implements CatalogoService {

   private final CatalogoRepositoryImpl catalogoRepository;
   private final PaisRepository paisRepository;

   @Transactional(readOnly = true)
   public List<PaisEntity> findAllPais() {
      List<PaisEntity> paises = paisRepository.findByNacionalidadIsNotNull();
      if (paises.size() == 0) {
         throw new NotFoundException();
      }
      return paises;
   }

   @Override
   @Transactional(readOnly = true)
   public List<CatalogoDto> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo) {
      List<CatalogoDto> catalogos = catalogoRepository.findAllCatalogosByGrupos(grupo, subgrupo);
      if (catalogos.size() == 0) {
         throw new NotFoundException();
      }
      return catalogos;
   }

}