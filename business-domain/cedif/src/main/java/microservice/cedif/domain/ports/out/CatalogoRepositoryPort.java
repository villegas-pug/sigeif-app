package microservice.cedif.domain.ports.out;

import java.util.List;
import microservice.cedif.domain.models.Catalogo;

public interface CatalogoRepositoryPort {
   List<Catalogo> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo);
}
