package microservice.cedif.domain.ports.in.catalogo;

import java.util.List;
import microservice.cedif.domain.models.Catalogo;

public interface CatalogoServicePort {

   List<Catalogo> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo);

}