package microservice.punche.anexofase.repository;

import java.util.List;
import microservice.punche.anexofase.model.AnexoFase;

public interface AnexoFaseRepository {

   List<AnexoFase> findAnexoFasesByNumAnexo(Integer numAnexo);

}
